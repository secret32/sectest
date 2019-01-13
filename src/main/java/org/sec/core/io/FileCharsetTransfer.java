package org.sec.core.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by secret on 14-7-10.
 */
public class FileCharsetTransfer {

    /**
     * Just for transfer gbk/gb2312 to utf8. Use FileCharsetDetector to detect the charset of files, if not utf-8, treat as gbk.
     */
    public static int toUTF8(String filename, boolean recursive) {
        try {
            Path path = Paths.get(filename);
            if (Files.exists(path)) {
                File file = path.toFile();
                if (Files.isSymbolicLink(path)) {
                    return -2;
                } else if (file.getName().startsWith(".") || file.getName().endsWith("~")) {
                    return -3;
                } else if (Files.isDirectory(path)) {
                    Files.list(path).forEach((p) -> {
                        if (Files.isDirectory(p)) {
                            if (recursive)
                                toUTF8(p.toFile().getAbsolutePath(), true);
                        } else {
                            toUTF8(p.toFile().getAbsolutePath(), false);
                        }
                    });
                } else {
                    if (!FileCharsetDetector.isUTF8(filename)) {
                        String bak = filename + ".bak";
                        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "GBK"));
                        BufferedWriter out = new BufferedWriter(new FileWriter(new File(bak)));
                        String line;
                        while ((line = in.readLine()) != null) {
                            out.write(line);
                            out.newLine();
                        }
                        out.close();
                        in.close();
                        Files.deleteIfExists(path);
                        Paths.get(bak).toFile().renameTo(file);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        toUTF8("/home/secret/test", true);
    }

}
