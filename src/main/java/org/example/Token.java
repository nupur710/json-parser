package org.example;

public class Token {
    static public TokenTypes tokenTypes;
    static private String value;
    public Token(TokenTypes tokenTypes, String value) {
        this.tokenTypes= tokenTypes;
        this.value= value;
    }

    public TokenTypes getTokenTypes() {
        return tokenTypes;
    }

    public void setTokenTypes(TokenTypes tokenTypes) {
        this.tokenTypes = tokenTypes;
    }

    static public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
