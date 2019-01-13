// Generated from D:/workspace/sectest/resource\Compute.g4 by ANTLR 4.5.1
package org.sec.application.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ComputeParser}.
 */
public interface ComputeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ComputeParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(ComputeParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComputeParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(ComputeParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComputeParser#sub}.
	 * @param ctx the parse tree
	 */
	void enterSub(ComputeParser.SubContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComputeParser#sub}.
	 * @param ctx the parse tree
	 */
	void exitSub(ComputeParser.SubContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComputeParser#mul}.
	 * @param ctx the parse tree
	 */
	void enterMul(ComputeParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComputeParser#mul}.
	 * @param ctx the parse tree
	 */
	void exitMul(ComputeParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComputeParser#div}.
	 * @param ctx the parse tree
	 */
	void enterDiv(ComputeParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComputeParser#div}.
	 * @param ctx the parse tree
	 */
	void exitDiv(ComputeParser.DivContext ctx);
}