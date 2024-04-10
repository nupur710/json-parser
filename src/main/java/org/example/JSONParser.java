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
    private List<Node> list= new ArrayList<>();

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
        String key;
        while((key=token()) != "}") {
            //String nextToken= token(); //should we use this instead
            //should lexer.peekNext() != '"" throw error?
            //String key= token();
            if(token() != ":") {
                //throw error
            }
            Node value= parseValue();
            keyValue.put(key, value);
            if(token() == ",") {
                continue;
//                throw new IOException("Invalid");
                //token();
                //parseObject();
            } else break;
        }
        return new ObjectNode(keyValue);
    }

    private Node parseValue() throws IOException {
        Token token= lexer.getToken();
        TokenTypes c= token.getTokenTypes();
        String str= token.getValue();
//        if(Character.isWhitespace(c)) {
//            c = token().charAt(0);
            //parseValue();
//        }
        if(c == TokenTypes.LEFT_CURLY_BRACKET) {
            lexer.buffer.add(str.charAt(0));
            return parseObject(); }
        else if(c == TokenTypes.OPEN_ARRAY) {
            lexer.buffer.add(str.charAt(0));
            return parseArray();
        }
        else if(c == TokenTypes.STRING_LITERAL) return new StringNode(str);
        else if(c == TokenTypes.NUMERIC_LITERAL) return new NumberNode(str);
        else if(c == TokenTypes.BOOLEAN) return new BooleanNode(str); //needs fixing
        else if(c == TokenTypes.BOOLEAN) return new BooleanNode(str); //needs fixing
        else if(c == TokenTypes.NULL) return new NullNode(str);
        else throw new IOException("Invalid char found"); //change
    }

    private Node parseArray() throws IOException {
        if(token() != "[") {
            //throw exception
        }

        String no;
        while((no=token()) != "]") {
            Node value= new ArrayValueNode(no);
            list.add(value);
            if(token() == ",") {
                //token();
                parseArrayValues();
            } else break;
        }
//        token();
        return new ArrayNode(list);
    }

    List<Node> parseArrayValues() throws IOException {
        String arrayValue= token();
        Node value= new ArrayValueNode(arrayValue);
        list.add(value);
        String next;
        while((next=token()) == ",") {
            parseArrayValues();
        }
        if(next == "]")  lexer.buffer.add(next.charAt(0));
        return list;
    }


}
