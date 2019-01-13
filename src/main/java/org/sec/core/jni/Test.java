package org.sec.core.jni;

/**
 * Created by Administrator on 2014/6/6.
 */
public class Test extends HelloJNI {
    @Override
    String getName() {
        return hello();
    }

    public static void main(String[] args) {
        HelloJNI test = new Test();
        System.out.println(test.getName());
    }
}
