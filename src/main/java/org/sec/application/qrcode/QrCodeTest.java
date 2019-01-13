package org.sec.application.qrcode;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QrCodeTest {

    public static void main(String[] args) {
        encode();
    }

    public static void encode() {
        String filePath = "D://";
        String fileName = "zxing.png";
        JSONObject json = new JSONObject();
        json.put("baidu", "https://www.baidu.com");
        json.put("author", "secret32@sina.com");
        String content = json.toJSONString();
        int width = 200;
        int height = 200;
        String format = "png";
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.QR_CODE, width, height, hints);
            Path path = FileSystems.getDefault().getPath(filePath, fileName);
            MatrixToImageWriter.writeToPath(bitMatrix, format, path);
            System.out.println("输出成功.");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void decode() {
        String filePath = "D://zxing.png";
        BufferedImage image;
        try {
            image = ImageIO.read(new File(filePath));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);
            JSONObject content = JSONObject.parseObject(result.getText());
            System.out.println("图片中内容：  ");
            System.out.println("author： " + content.getString("author"));
            System.out.println("baidu：  " + content.getString("baidu"));
            System.out.println("图片中格式：  ");
            System.out.println("encode： " + result.getBarcodeFormat());
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
    }

}
