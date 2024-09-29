package org.jsonparser.converttoxml;

import org.jsonparser.parsetree.*;

import java.util.List;
import java.util.Map;

public class ConvertToXML {
    private StringBuilder xml= new StringBuilder();
    public void exportToXML(Node json) {
        Map<String, Node> jsonMap= (Map<String, Node>) json.getNode();
        xml.append("<Object>");
        for(Map.Entry<String, Node> entry : jsonMap.entrySet()) {
            String key= entry.getKey();
            Node value= entry.getValue();
            buildXML(key, value);
        }
        xml.append("\n\r<Object>");
        System.out.println(xml.toString());
    }

    private void buildXML(String key, Node value) {
        if(value instanceof StringNode) {
           parseStringNode(key, value, true);
        } else if(value instanceof NumberNode) {
            parseNumberNode(key, value, true);
        } else if(value instanceof BooleanNode) {
            parseBooleanNode(key, value, true);
        } else if(value instanceof ArrayNode) {
            parseArrayNode(key, value);
        }
    }

    private void parseStringNode(String key, Node value, boolean k) {
        if(k) {
            xml.append("\n\r<" + key + ">" + "\n\t<String>"
                    + value.getNode() + "</String>" + "\n\r</" + key + ">");
        } else {
            xml.append("\n\t<String>"
                    + value.getNode() + "</String>");
        }
    }

    private void parseNumberNode(String key, Node value, boolean k) {
        if(k) {
            xml.append("\n\r<" + key + ">" + "\n\t<Number>"
                    + value.getNode() + "</Number>" + "\n\r</" + key + ">");
        } else {
            xml.append("\n\t<Number>"
                    + value.getNode() + "</Number>");
        }
    }

    private void parseBooleanNode(String key, Node value, boolean k) {
        if(k) {
            xml.append("\n\r<" + key + ">" + "\n\t<Boolean>"
                    + value.getNode() + "</Boolean>" + "\n\r</" + key + ">");
        } else {
            xml.append("\n\t<Boolean>"
                    + value.getNode() + "</Boolean>");
        }
    }

    private void parseArrayNode(String key, Node value) {
        xml.append("\n\r<"+key+">"+"\n\t<Array>");
        if (value instanceof ArrayNode) {
            {
                List<Node> valueList = (List<Node>) value.getNode();
                if(valueList.get(0) instanceof StringNode) {
                    for (Node node : valueList) {
                        parseStringNode(key, node, false);
                    }
                } else if(valueList.get(0) instanceof NumberNode) {
                    for (Node node : valueList) {
                        parseNumberNode(key, node, false);
                    }
                }
            }
        } xml.append("\n\r</Array>"+"\n\r<"+key+"/>");
    }
}
