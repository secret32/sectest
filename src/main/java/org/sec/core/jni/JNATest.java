package org.sec.core.jni;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * @author zhouwei
 * @since 2016/8/11
 */
public class JNATest {

    public static void main(String[] args) {
        CLibrary.INSTANCE.printf("Hello World, %s\n", "sec");
    }

    interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);
        void printf(String format, Object... args);
    }

}
