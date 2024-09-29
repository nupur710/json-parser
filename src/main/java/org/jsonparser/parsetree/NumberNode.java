package org.jsonparser.parsetree;

public class NumberNode implements Node{
    private String no;
    public NumberNode(String no) {
        this.no= no;
    }

    @Override
    public String getNode() {
        return no;
    }
}
