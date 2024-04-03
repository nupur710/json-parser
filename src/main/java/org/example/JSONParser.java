package org.example;

import org.example.parsetree.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONParser {

    private Lexer lexer;

    public JSONParser(InputStreamReader reader) {
        this.lexer= new Lexer(reader);
    }

    private String token() throws IOException {
        return lexer.getToken().getValue();
    }

    public Node parse() throws IOException {
        return parseObject();
    }

    private Node parseObject() throws IOException {
        if(token() != "{") { //first character is not opening bracket
            //throw exception
        }
        Map<String, Node> keyValue= new HashMap<>();
        while(token() != "}") {
            //should lexer.peekNext() != '"" throw error?
            String key= lexer.constructString();
            if(token() != ":") {
                //throw error
            }
            Node value= parseValue();
            keyValue.put(key, value);
            if(token() == ",") {
                token();
            }
        }
        return new ObjectNode(keyValue);
    }

    private Node parseValue() throws IOException {
        char c= token().charAt(0);
        if(Character.isWhitespace(c)) {
            c = token().charAt(0);
            //parseValue();
        }
        if(c == '{') return parseObject();
        else if(c == '[') return parseArray();
        else if(c == '"') return new StringNode(lexer.constructString());
        else if(Character.isDigit(c)) return new NumberNode(lexer.constructNumber(c));
        else if(c == 't') return new BooleanNode(lexer.checkTrue());
        else if(c == 'f') return new BooleanNode(lexer.checkFalse());
        else if(c == 'n') return new NullNode(lexer.checkNull());
        else throw new IOException("Invalid char found"); //change
    }

    private Node parseArray() throws IOException {
        if(token() != "[") {
            //throw exception
        }
        List<Node> list= new ArrayList<>();
        while(token() != "]") {
            Node value= parseValue();
            list.add(value);
            if(token() == ",") {
                token();
            }
        }
        token();
        return new ArrayNode(list);
    }


}
