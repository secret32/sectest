package org.sec.application.other;

/**
 * 面试题： 对一个字符串输入的数学表达式进行计算，运算符只包括+-*，并且不考虑运算符的优先级（从左往右计算），参与计算的数字仅有0-9
 *
 * @author zhouwei
 * @since 12/5/2018
 */
public class StringComputer {

    private static int compute(int a, int b, String op) throws Exception {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            default:
                throw new Exception("error op");
        }
    }

    private static int parseAndCompute(String expression) throws Exception {
//        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i < expression.length();) {
            if (i == 0) {
                int a = Integer.parseInt(String.valueOf(expression.charAt(i)));
                String op = String.valueOf(expression.charAt(i + 1));
                int b = Integer.parseInt(String.valueOf(expression.charAt(i + 2)));
                result = compute(a, b, op);
                i += 3;
            } else {
                String op = String.valueOf(expression.charAt(i));
                int b = Integer.parseInt(String.valueOf(expression.charAt(i + 1)));
                result = compute(result, b, op);
                i += 2;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(parseAndCompute("8+5*7"));
        System.out.println(parseAndCompute("1-4*6"));
    }

}
