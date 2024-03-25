package org.example;

import java.util.ArrayList;
import java.util.List;

public class BuildAST {

    private TokenTypes token;
    private String value;
    private List<BuildAST> children;

    public BuildAST(TokenTypes token, String value) {
        this.token= token;
        this.value= value;
        this.children= new ArrayList<>();
    }

    public TokenTypes getTokenType() {
        return this.token;
    }

    public String getTokenValue() {
        return this.value;
    }

    public void addChild(BuildAST child) {
        children.add(child);
    }

    public List<BuildAST> getChildren() {
        return children;
    }

}
