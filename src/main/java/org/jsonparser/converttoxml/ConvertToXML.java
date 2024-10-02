package org.jsonparser.converttoxml;

import org.jsonparser.parsetree.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ConvertToXML {
    private StringBuilder xml= new StringBuilder();
    private int i= 0;
    public String exportToXml(Node json) {
        xml.append("<Object>");
        parseObjectNode(json);
        xml.append("\n</Object>");
        return xml.toString();
    }

    private void buildXML(String key, Node value) {
        if(value instanceof StringNode) {
            i++;
            parseStringNode(key, value, true, 0);
            i--;
        } else if(value instanceof NumberNode) {
            i++;
            parseNumberNode(key, value, true, 0);
            i--;
        } else if(value instanceof BooleanNode) {
            i++;
            parseBooleanNode(key, value, true);
            i--;
        } else if(value instanceof ArrayNode) {
            i++;
            parseArrayNode(key, value);
            i--;
        } else if(value instanceof ObjectNode) {
            i++;
            appendObjStart(key, i);
            i++;
            parseObjectNode(value);
            i--;
            appendObjEnd(key, i);
            i--;
        }
    }

    private void parseStringNode(String key, Node value, boolean k, int r) {
        if(k) {
            xml.append("\n");
            appendTabCharacters(i);
            xml.append("<"+key+">");
            xml.append("\n");
            appendTabCharacters(i+1);
            xml.append("<String>" + value.getNode() + "</String>");
            xml.append("\n");
            appendTabCharacters(i);
            xml.append("</"+key+">");
        } else {
            xml.append("\n");
            appendTabCharacters(r);
            xml.append("<String>" + value.getNode() + "</String>");
        }
    }

    private void parseBooleanNode(String key, Node value, boolean k) {
        if(k) {
            xml.append("\n");
            appendTabCharacters(i);
            xml.append("<"+key+">");
            xml.append("\n");
            appendTabCharacters(i+1);
            xml.append("<Boolean>" + value.getNode() + "</Boolean>");
            xml.append("\n");
            appendTabCharacters(i);
            xml.append("</"+key+">");
        } else {
            xml.append("\n");
            appendTabCharacters(i);
            xml.append("<Boolean>" + value.getNode() + "</Boolean>");
        }
    }

    private void parseNumberNode(String key, Node value, boolean k, int r) {
        if(k) {
            xml.append("\n");
            appendTabCharacters(i);
            xml.append("<"+key+">");
            xml.append("\n");
            appendTabCharacters(i+1);
            xml.append("<Number>" + value.getNode() + "</Number>");
            xml.append("\n");
            appendTabCharacters(i);
            xml.append("</"+key+">");
        } else {
            xml.append("\n");
            appendTabCharacters(r);
            xml.append("<Number>" + value.getNode() + "</Number>");
        }
    }

    private void parseArrayNode(String key, Node value) {
        int j= i;
        xml.append("\n");
        appendTabCharacters(j);
        xml.append("<"+key+">");
        xml.append("\n");
        appendTabCharacters(j+1);
        xml.append("<Array>");
        j= i+1;
        if(value instanceof ArrayNode) {
            List<Node> valueList= (List<Node>) value.getNode();
            if(valueList.get(0) instanceof  StringNode) {
                for(Node node : valueList) {
                    j++;
                    parseStringNode(key, node, false, j);
                    j--;
                }
            } else if(valueList.get(0) instanceof NumberNode) {
                for(Node node : valueList) {
                    j++;  //should this be i++? then i--
                    parseNumberNode(key, node, false, j);
                    j--;
                }
            }
        }
        xml.append("\n");
        appendTabCharacters(j);
        xml.append("</Array>");
        xml.append("\n");
        appendTabCharacters(j-1);
        xml.append("</"+key+">");

        xml.append("\n");
        //appendTabCharacters();
    }

    private void parseObjectNode(Node json) {
        Map<String, Node> jsonMap= (Map<String, Node>) json.getNode();
        for(Map.Entry<String, Node> entry : jsonMap.entrySet()) {
            String key= entry.getKey();
            Node value= entry.getValue();
            //    i++; //remove
            buildXML(key, value);
        }
    }

    private void appendObjStart(String key, int j) {
        xml.append("\n");
        appendTabCharacters(j);
        xml.append("<"+key+">");
        xml.append("\n");
        appendTabCharacters(j+1);
        xml.append("<Object>");
        xml.append("\n");
//        appendTabCharacters(j+1);
//        xml.append("</"+key+">");
    }

    private void appendObjEnd(String key, int j) {
        xml.append("\n");
        appendTabCharacters(j+1);
        xml.append("</Object>");
        xml.append("\n");
        appendTabCharacters(j);
        xml.append("</"+key+">");
    }

    private void appendTabCharacters(int x) {
        for(int j= 0; j< x; j++){ //remove equal to
            xml.append("\t");
        }
    }

    public void writeToFile(String str)  {
        try(PrintWriter writer= new PrintWriter("XMLOutput.xml")) {
            writer.println(str);
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundError: " + e.getMessage());
        }
    }
}
