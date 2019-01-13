package org.sec.core.lambda;

import java.util.function.Function;

/**
 * @author zhouwei
 * @since 2016/1/5
 */
public class ComposeAndThenTest {

    public static void main(String[] args) {
        Function<Integer, Integer> add = a -> a + 1;
        Function<Integer, Integer> multi = a -> a * a;
        System.out.println(add.andThen(multi).apply(3));
        System.out.println(add.compose(multi).apply(3));
    }

}
