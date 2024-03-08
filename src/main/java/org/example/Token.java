package org.example;

public class Token {
    private TokenTypes tokenTypes;
    private String value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
