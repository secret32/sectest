package org.sec.core.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by secret on 14-7-9.
 */
public class FileCharsetDetector {

    @SuppressWarnings("unused")
	private static final byte[] WIN_UTF8_PREFIX = new byte[]{0xffffffef, 0xffffffbb, 0xffffffbf};

    public static boolean isUTF8(String filename) throws IOException {
        if (filename == null || filename.trim().length() == 0)
            return false;
        Path path = Paths.get(filename);
        if (Files.isDirectory(path)) {
            throw new IllegalArgumentException("can not get the charset of a directory");
        } else {
            try (FileInputStream in = new FileInputStream(path.toFile())) {
                byte[] buf = new byte[128];
                while (in.read(buf, 0, buf.length) != -1) {
                    try {
                        return isUTF8(buf);
                    } catch (Exception e) {
                        continue;
                    }
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return false;
    }

    /**
     * UTF-8 Charset: chinese characters are most like "1110xxxx 10xxxxxx 10xxxxxx".
     * For detect chinese characters. if need to detect other special characters, this method should be added more "else if'.
     * When this method returns false doesn't mean the charset of the bytes isn't UTF-8.
     *
     * @param bytes
     * @return
     */
    private static boolean isUTF8(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] > 0x0) {
                continue;
            }
            if (bytes[i] <= Integer.valueOf(0xffffffef) && bytes[i] >= Integer.valueOf(0xffffffe0)) {
                if (bytes[i + 1] <= Integer.valueOf(0xffffffbf) && bytes[i + 1] >= Integer.valueOf(0xffffff80) && bytes[i + 2] <= Integer.valueOf(0xffffffbf) && bytes[i + 2] >= Integer.valueOf(0xffffff80))
                    return true;
                else
                    return false;
            } else
                return false;
        }
        throw new RuntimeException();
    }

}
