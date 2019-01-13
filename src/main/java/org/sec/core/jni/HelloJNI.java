package org.sec.core.jni;

/**
 * Created by Administrator on 2014/6/6.
 */
public abstract class HelloJNI {

    static {
        System.loadLibrary("hello");
    }

    public native String hello();

    abstract String getName();

}
