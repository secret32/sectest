package org.sec.core.asm;

/**
 * Created by sec on 2014/5/29.
 */
public class SecClassLoader extends ClassLoader {

    private byte[] bytes = null;

    public SecClassLoader(byte[] bytes) throws Exception {
        if (bytes == null)
            throw new Exception("can not load null bytes");
        this.bytes = bytes;
    }

    @Override
    public Class<?> loadClass(String name) {
        return defineClass(name, bytes, 0, bytes.length);
    }

}
