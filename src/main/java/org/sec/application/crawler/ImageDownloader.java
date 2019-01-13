package org.sec.application.crawler;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by secret on 14-6-30.
 */
public class ImageDownloader {

    private String savePath = "/opt";

    public ImageDownloader(String savePath) {
        this.savePath = savePath;
        try {
            Path path = Paths.get(savePath);
            if (Files.notExists(path))
                Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadImage(String src) {
        if (StringUtils.isBlank(src))
            return;
        try {
            if (src.contains("\\"))
                src.replaceAll("\\\\", "/");
            int index = src.lastIndexOf("/");
            String domain = src.substring(0, index);
            String name = src.substring(index + 1);
            if (name.contains(" "))
                src = domain + name.replaceAll(" ", URLEncoder.encode(" ", "UTF-8"));
            if (name.length() > 50) {
                String suffix = name.substring(name.lastIndexOf("."));
                name = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            }
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse response = httpClient.execute(new HttpGet(src));
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                HttpEntity entity = response.getEntity();
                if (entity.isStreaming())
                    entity.writeTo(new FileOutputStream(savePath + File.separator + name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
