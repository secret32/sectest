package org.sec.application.json;

/**
 * @author zhou_wei
 * @since v0.1
 */
public enum Token {

    OPEN_BRACE("{"),
    CLOSE_BRACE("}"),
    OPEN_BRACKET("["),
    CLOSE_BRACKET("]"),
    QUOTES("[\"\']"),
    COMMA(","),
    COLON(":"),
    NUMBER("\\d+"),
    KEY(".+"),
    VALUE(".+"),
    BLANK("\\s+"),
    OVER(""),
    ;

    private String token;

    Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
