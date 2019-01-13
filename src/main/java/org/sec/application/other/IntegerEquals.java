package org.sec.application.other;

/**
 * 面试题：Integer == equals
 *
 * @author zhouwei
 * @since 12/5/2018
 */
public class IntegerEquals {

    public static void main(String[] args) {
        Integer a = new Integer(0);
        Integer b = Integer.valueOf(0);
        Integer c = 0;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a == c);
        System.out.println(a.equals(c));
        System.out.println(b == c);
        System.out.println(b.equals(c));
        System.out.println("**************");
        a = new Integer(800);
        b = Integer.valueOf(800);
        c = 800;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a == c);
        System.out.println(a.equals(c));
        System.out.println(b == c);
        System.out.println(b.equals(c));
    }

}
