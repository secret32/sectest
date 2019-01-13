package org.sec.application.other;

/**
 * 面试题：强转null为对象后调用成员方法，是否会出现编译或运行时异常
 *
 * @author zhouwei
 * @since 12/5/2018
 */
public class NotNullPointer {

    private void test() {
        System.out.println("test");
    }

    private static void staticTest() {
        System.out.println("static test");
    }

    public static void main(String[] args) {
        ((NotNullPointer) null).staticTest();
        ((NotNullPointer) null).test();
    }

}
