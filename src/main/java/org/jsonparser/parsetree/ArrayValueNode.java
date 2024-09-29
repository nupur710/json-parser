package org.jsonparser.parsetree;

public class ArrayValueNode implements Node {
    private String value;
    public ArrayValueNode(String value) {
        this.value= value;
    }
}
