package org.sec.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author zhou_wei
 */
public class ConcatTest {

    public static void main(String[] args) {
        String[] a = {"a1", "a2", "a3"};
        String[] b = {"b1", "b2"};
        String[] c = {"c1", "c2"};
        String[] d = {"d1", "d2", "d3"};
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList(a));
        list.add(Arrays.asList(b));
        list.add(Arrays.asList(c));
        list.add(Arrays.asList(d));

        Optional<List<String>> op = list.stream().reduce((array1, array2) -> {
            List<String> result = new ArrayList<>();
            array1.stream().forEach(s1 -> {
                array2.stream().forEach(s2 -> {
                    result.add(s1 + s2);
                });
            });
            return result;
        });
        System.out.println(op.get());
    }

}
