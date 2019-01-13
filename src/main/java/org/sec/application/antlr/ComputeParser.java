// Generated from D:/workspace/sectest/resource\Compute.g4 by ANTLR 4.5.1
package org.sec.application.antlr;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ComputeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		K_ADD=1, K_SUB=2, K_MUL=3, K_DIV=4, INTEGER=5, K_CMD=6, WS=7;
	public static final int
		RULE_add = 0, RULE_sub = 1, RULE_mul = 2, RULE_div = 3;
	public static final String[] ruleNames = {
		"add", "sub", "mul", "div"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, "'calc'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "K_ADD", "K_SUB", "K_MUL", "K_DIV", "INTEGER", "K_CMD", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Compute.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ComputeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AddContext extends ParserRuleContext {
		public TerminalNode K_CMD() { return getToken(ComputeParser.K_CMD, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(ComputeParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(ComputeParser.INTEGER, i);
		}
		public TerminalNode K_ADD() { return getToken(ComputeParser.K_ADD, 0); }
		public AddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComputeListener ) ((ComputeListener)listener).enterAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComputeListener ) ((ComputeListener)listener).exitAdd(this);
		}
	}

	public final AddContext add() throws RecognitionException {
		AddContext _localctx = new AddContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_add);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			match(K_CMD);
			setState(9);
			match(INTEGER);
			setState(10);
			match(K_ADD);
			setState(11);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubContext extends ParserRuleContext {
		public TerminalNode K_CMD() { return getToken(ComputeParser.K_CMD, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(ComputeParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(ComputeParser.INTEGER, i);
		}
		public TerminalNode K_SUB() { return getToken(ComputeParser.K_SUB, 0); }
		public SubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComputeListener ) ((ComputeListener)listener).enterSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComputeListener ) ((ComputeListener)listener).exitSub(this);
		}
	}

	public final SubContext sub() throws RecognitionException {
		SubContext _localctx = new SubContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sub);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			match(K_CMD);
			setState(14);
			match(INTEGER);
			setState(15);
			match(K_SUB);
			setState(16);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulContext extends ParserRuleContext {
		public TerminalNode K_CMD() { return getToken(ComputeParser.K_CMD, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(ComputeParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(ComputeParser.INTEGER, i);
		}
		public TerminalNode K_MUL() { return getToken(ComputeParser.K_MUL, 0); }
		public MulContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mul; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComputeListener ) ((ComputeListener)listener).enterMul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComputeListener ) ((ComputeListener)listener).exitMul(this);
		}
	}

	public final MulContext mul() throws RecognitionException {
		MulContext _localctx = new MulContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_mul);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			match(K_CMD);
			setState(19);
			match(INTEGER);
			setState(20);
			match(K_MUL);
			setState(21);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DivContext extends ParserRuleContext {
		public TerminalNode K_CMD() { return getToken(ComputeParser.K_CMD, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(ComputeParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(ComputeParser.INTEGER, i);
		}
		public TerminalNode K_DIV() { return getToken(ComputeParser.K_DIV, 0); }
		public DivContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_div; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComputeListener ) ((ComputeListener)listener).enterDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComputeListener ) ((ComputeListener)listener).exitDiv(this);
		}
	}

	public final DivContext div() throws RecognitionException {
		DivContext _localctx = new DivContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_div);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			match(K_CMD);
			setState(24);
			match(INTEGER);
			setState(25);
			match(K_DIV);
			setState(26);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\t\37\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\2\2\6\2\4\6\b\2\2\32\2\n\3\2\2\2\4"+
		"\17\3\2\2\2\6\24\3\2\2\2\b\31\3\2\2\2\n\13\7\b\2\2\13\f\7\7\2\2\f\r\7"+
		"\3\2\2\r\16\7\7\2\2\16\3\3\2\2\2\17\20\7\b\2\2\20\21\7\7\2\2\21\22\7\4"+
		"\2\2\22\23\7\7\2\2\23\5\3\2\2\2\24\25\7\b\2\2\25\26\7\7\2\2\26\27\7\5"+
		"\2\2\27\30\7\7\2\2\30\7\3\2\2\2\31\32\7\b\2\2\32\33\7\7\2\2\33\34\7\6"+
		"\2\2\34\35\7\7\2\2\35\t\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}