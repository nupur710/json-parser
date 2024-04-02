package org.example.parsetree;

import java.util.List;

public class ArrayNode implements Node{
    private List<Node> list;

    public ArrayNode(List<Node> list) {
        this.list= list;
    }
}
