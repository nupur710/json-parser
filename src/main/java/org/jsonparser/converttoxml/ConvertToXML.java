package org.jsonparser.converttoxml;

import org.jsonparser.parsetree.*;

import java.util.List;
import java.util.Map;

public class ConvertToXML {
    private StringBuilder xml= new StringBuilder();
    public void exportToXML(Node json) {
        xml.append("<Object>");
        parseObjectNode(json);
        xml.append("\n\r</Object>");
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
        } else if (value instanceof ObjectNode) {
            appendObjStart(key);
            parseObjectNode(value);
            appendObjEnd(key);
        }
    }

    private void parseStringNode(String key, Node value, boolean k) {
        if(k) {
            xml.append("\n\t<" + key + ">" + "\n\t\t<String>"
                    + value.getNode() + "</String>" + "\n\t</" + key + ">");
        } else {
            xml.append("\n\t<String>"
                    + value.getNode() + "</String>");
        }
    }

    private void parseNumberNode(String key, Node value, boolean k) {
        if(k) {
            xml.append("\n\t<" + key + ">" + "\n\t\t<Number>"
                    + value.getNode() + "</Number>" + "\n\t</" + key + ">");
        } else {
            xml.append("\n\t<Number>"
                    + value.getNode() + "</Number>");
        }
    }

    private void parseBooleanNode(String key, Node value, boolean k) {
        if(k) {
            xml.append("\n\t<" + key + ">" + "\n\t\t<Boolean>"
                    + value.getNode() + "</Boolean>" + "\n\t</" + key + ">");
        } else {
            xml.append("\n\t<Boolean>"
                    + value.getNode() + "</Boolean>");
        }
    }

    private void parseObjectNode(Node json) {
        Map<String, Node> jsonMap= (Map<String, Node>) json.getNode();
        for(Map.Entry<String, Node> entry : jsonMap.entrySet()) {
            String key= entry.getKey();
            Node value= entry.getValue();
            buildXML(key, value);
        }
    }

    private void parseArrayNode(String key, Node value) {
        xml.append("\n\t<"+key+">"+"\n\t\t<Array>");
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
        } xml.append("\n\r</Array>"+"\n\t<"+key+"/>");
    }
    private void appendObjStart(String key) {
        xml.append("\n\t<"+key+">\n\t\t<Object>");
    }
    private void appendObjEnd(String key) {
        xml.append("\n\t</Object"+">\n\t\t</"+key+">");
    }
}
