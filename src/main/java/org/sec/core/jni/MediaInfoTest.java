package org.sec.core.jni;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author zhouwei
 * @since 2016/8/11
 */
public class MediaInfoTest {

    public static void main(String[] args) {
        String ret = "";
        MediaInfo mediaInfo = new MediaInfo();
        int open = mediaInfo.open("");
        if (open > 0) {
            mediaInfo.option("Complete", "");
            ret = mediaInfo.inform();
            mediaInfo.close();
        }
        System.out.println(ret);
    }

    private static class MediaInfo {
        public MediaInfo() {
        }

        public int open(String videoPath) {
            return MediaInfoLibrary.INSTANCE.Open(videoPath);
        }

        public void option(String status, String unknown) {
            MediaInfoLibrary.INSTANCE.Option(status, unknown);
        }

        public String inform() {
            return MediaInfoLibrary.INSTANCE.Inform();
        }

        public void close() {
            MediaInfoLibrary.INSTANCE.Close();
        }
    }

    interface MediaInfoLibrary extends Library {
        MediaInfoLibrary INSTANCE =
                (MediaInfoLibrary) Native.loadLibrary("MediaInfo", MediaInfoLibrary.class);

        int Open(String videoPath);
        void Option(String status, String unknown);
        String Inform();
        void Close();
    }

}
