package org.sec.application.antlr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * @author zhouwei
 * @since 2016/3/28
 */
public class Computer {

    public static void main(String[] args) {
        String s = "calc 44 add 33";
        ComputeLexer lexer = new ComputeLexer(new ANTLRInputStream(s));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ComputeParser parser = new ComputeParser(tokens);
        ComputeParser.AddContext addTree = parser.add();
        int i1 = Integer.parseInt(addTree.getToken(ComputeLexer.INTEGER, 0).getText());
        int i2 = Integer.parseInt(addTree.getToken(ComputeLexer.INTEGER, 1).getText());
        System.out.println(i1 + i2);
    }
}
