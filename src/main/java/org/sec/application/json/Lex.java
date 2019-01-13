package org.sec.application.json;

/**
 * @author zhou_wei
 * @since v0.1
 */
public class Lex {

    private String target;
    private int position;
    private Status status;

    private char tmp; // for save ' or "

    public Lex(String target) {
        this.target = target;
        this.position = 0;
        if (target.length() < "{}".length()) {
            throw new IllegalArgumentException("invalid json string");
        }
        status = Status.START;
    }

    public Token nextToken() {
        Token token = Token.OVER;
        switch (status) {
            case START:
                String s0 = nextNotBlank();
                if (s0.equals(Token.OPEN_BRACE.getToken())) {
                    status = Status.OBJECT_START;
                    token = Token.OPEN_BRACE;
                } else if (s0.equals(Token.OPEN_BRACKET.getToken())) {
                    status = Status.ARRAY_START;
                    token = Token.OPEN_BRACKET;
                } else {
                    throw new IllegalArgumentException("invalid character at " + position);
                }
                break;
            case OBJECT_START:
                String s1 = nextNotBlank();
                if (s1.matches(Token.QUOTES.getToken())) {
                    status = Status.KEY_START;
                    token = Token.QUOTES;
                    tmp = s1.charAt(0);
                } else {
                    throw new IllegalArgumentException("invalid character at " + position);
                }
                break;
            case KEY_START:
                StringBuilder keySb = new StringBuilder();
                char keyLast = ' ';
                int keyLen = 0;
                while (true) {
                    char thisChar = target.charAt(position++);
                    if (thisChar == tmp && keyLast != '\\') {
                        SymbolTable.getInstance().addSymbol(keySb.toString());
                        break;
                    } else {
                        if (keyLen > 0) {
                            keyLast = keySb.charAt(keyLen - 1);
                        }
                        keySb.append(thisChar);
                        keyLen++;
                    }
                }
                status = Status.KEY_END;
                token = Token.KEY;
                tmp = ' ';
                break;
            case KEY_END:
                String s2 = nextNotBlank();
                if (s2.equals(Token.COLON.getToken())) {
                    token = Token.COLON;
                    status = Status.VALUE_START;
                } else {
                    throw new IllegalArgumentException("invalid character at " + position);
                }
                break;
            case VALUE_START:
                // now only support "string"
                StringBuilder valueSb = new StringBuilder();
                char valueLast = ' ';
                int valueLen = 0;
                while (true) {
                    char thisChar = target.charAt(position++);
                    if (thisChar == tmp && valueLast != '\\') {
                        SymbolTable.getInstance().addSymbol(valueSb.toString());
                        break;
                    } else {
                        if (valueLen > 0) {
                            valueLast = valueSb.charAt(valueLen - 1);
                        }
                        valueSb.append(thisChar);
                        valueLen++;
                    }
                }
                status = Status.VALUE_END;
                token = Token.VALUE;
                tmp = ' ';
                break;
            case VALUE_END:
                String s3 = nextNotBlank();
                if (s3.equals(Token.CLOSE_BRACE.getToken())) {
                    token = Token.CLOSE_BRACE;
                    status = Status.OBJECT_END;
                } else {
                    throw new IllegalArgumentException("invalid character at " + position);
                }
                break;
            case OBJECT_END:
                // In an array, that doesn't mean the end.
            case END:
                token = Token.OVER;
        }
        return token;
    }

    private String nextNotBlank() {
        String s = String.valueOf(target.charAt(position++));
        while (s.matches(Token.BLANK.getToken())) {
            s = String.valueOf(target.charAt(position++));
        }
        return s;
    }

}
