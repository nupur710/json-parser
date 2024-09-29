package org.jsonparser.parsetree;

public class NullNode implements Node{
    private String nullVal;
    public NullNode(String nullVal) {
        this.nullVal= nullVal;
    }

    @Override
    public String getNode() {
        return null;
    }
}
