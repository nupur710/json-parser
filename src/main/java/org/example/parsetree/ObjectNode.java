package org.example.parsetree;

import org.example.ASTNode;

import java.util.Map;

public class ObjectNode implements Node {

    private Map<String, Node> map;

    public ObjectNode(Map<String, Node> map) {
        this.map= map;
    }
}
