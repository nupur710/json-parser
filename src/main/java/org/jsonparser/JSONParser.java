package org.jsonparser;

import org.jsonparser.lexer.Lexer;
import org.jsonparser.lexer.Token;
import org.jsonparser.lexer.TokenTypes;
import org.jsonparser.parsetree.*;

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

    private Token token() throws IOException {
        return lexer.getToken();
    }

    public Node parse() throws IOException {
        return parseObject();
    }

    private Node parseObject() throws IOException {
        if(token().getValue() != "{") { //first character is not opening bracket
            throw new IOException("Illegal Character");
        }
        Map<String, Node> keyValue= new HashMap<>();
        String key;
        while((key=token().getValue()) != "}") {
            if(token().getValue() != ":") {
                throw new IOException("Illegal Character");
            }
            Node value= parseValue();
            keyValue.put(key, value);
            if(token().getValue() == ",") {
                continue;
            } else break;
        }
        return new ObjectNode(keyValue);
    }

    private Node parseValue() throws IOException {
        Token token= lexer.getToken();
        TokenTypes c= token.getTokenTypes();
        String str= token.getValue();
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
        List<Node> list= new ArrayList<>();
        if(token().getTokenTypes() != TokenTypes.OPEN_ARRAY) {
            throw new IOException("Illegal Character");
        }
        Token no= token();
        TokenTypes noType= no.getTokenTypes();
        while((no.getTokenTypes()) != TokenTypes.CLOSE_ARRAY) {
            Node value= null;
            if(noType== TokenTypes.STRING_LITERAL) value= new StringNode(no.getValue());
            else if(noType== TokenTypes.NUMERIC_LITERAL) value= new NumberNode(no.getValue());
            list.add(value);
            if(token().getTokenTypes() == TokenTypes.COMMA) {
                parseArrayValues(list);
            }
            else if(token().getTokenTypes() == TokenTypes.STRING_LITERAL) {
            }
            else break;
        }

        return new ArrayNode(list);
    }

    List<Node> parseArrayValues(List<Node> list) throws IOException {
        Token val= token();
        TokenTypes valType= val.getTokenTypes();
        String arrayValue= val.getValue();
        Node value= null;
        if(valType== TokenTypes.STRING_LITERAL) {
            value = new StringNode(arrayValue);
        } else if(valType== TokenTypes.NUMERIC_LITERAL) {
            value= new NumberNode(arrayValue);
        }
        list.add(value);
        Token next= token();
        while((next.getTokenTypes()) == TokenTypes.COMMA) {
            parseArrayValues(list);
        }
        if(next.getTokenTypes() != TokenTypes.CLOSE_ARRAY)  throw new IOException("Illegal Exception");
        return list;
    }
}
