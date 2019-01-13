package org.sec.utils;

import java.util.Arrays;

/**
 * Created by Administrator on 2014/6/3.
 */
public class ByteBuilder {

    byte[] bytes;

    int index = 0;

    public ByteBuilder(byte[] bts) {
        bytes = bts;
    }

    public ByteBuilder(int capacity) {
        bytes = new byte[capacity];
    }

    public ByteBuilder() {
        this(1024);
    }

    public void append(byte b) {
        if (index == bytes.length - 1) {
            bytes = Arrays.copyOf(bytes, bytes.length + 1);
        }
        bytes[index++] = b;
    }

    public void append(byte[] bts) {
        append(bts, 0, bts.length);
    }

    public void append(byte[] bts, int length) {
        append(bts, 0, length);
    }

    public void append(byte[] bts, int start, int length) {
        if (length > bytes.length - index) {
            bytes = Arrays.copyOf(bytes, length + index);
        }
        System.arraycopy(bts, start, bytes, index, length);
        index += length;
    }

    public int size() {
        return index;
    }

    public int length() {
        return bytes.length;
    }

    public byte[] getBytes() {
        return Arrays.copyOf(bytes, index);
    }

    @Override
    public String toString() {
        return new String(getBytes());
    }

}
