package org.jsonparser.parsetree;

public class StringNode implements Node{

    private String string;

    public StringNode(String string) {
        this.string= string;
    }

    @Override
    public String getNode() {
        return string;
    }
}
