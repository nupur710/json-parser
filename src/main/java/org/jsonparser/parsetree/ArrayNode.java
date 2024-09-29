package org.jsonparser.parsetree;

import java.util.List;

public class ArrayNode implements Node{
    private List<Node> list;
    //should this be a list of ArrayValueNode instead??
    public ArrayNode(List<Node> list) {
        this.list= list;
    }

    @Override
    public List<Node> getNode() {
        return list;
    }
}
