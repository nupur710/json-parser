package org.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONParser {

    private Lexer lexer;

    public JSONParser(InputStreamReader reader) {
        this.lexer= new Lexer(reader);
    }

    public ASTNode parse() throws IOException {
        return parseObject();
    }

    private ASTNode parseObject() throws IOException {
        if(lexer.peekNext() != '{') { //first character is not opening bracket
            //throw exception
        }
        Map<String, ASTNode> keyValue= new HashMap<>();
        while(lexer.peekNext() != '}') {
            String key= lexer.constructString();
            if(lexer.peekNext() != ':') {
                //throw error
            }
            ASTNode value= parseValue();
            keyValue.put(key, value);
            if(lexer.peekNext() == ',') {
                lexer.peekNext();
            }
        }
        return new ObjectNode(keyValue);
    }

    private ASTNode parseValue() throws IOException {
        char c= lexer.peekNext();
        if(c == '{') return parseObject();
        else if(c == '[') return parseArray();
        else if(c == '"') return new StringNode(lexer.constructString());
        else if(Character.isDigit(c)) return new NumberNode(lexer.constructNumber(c));
        else if(c == 't') return new BooleanNode(lexer.checkTrue());
        else if(c == 'f') return new BooleanNode(lexer.checkFalse());
        else if(c == 't') return new NullNode(lexer.checkNull());
        //else //throw exception;
        return null;
    }

    private ASTNode parseArray() throws IOException {
        if(lexer.peekNext() != '[') {
            //throw exception
        }
        List<ASTNode> list= new ArrayList<>();
        while(lexer.peekNext() != ']') {
            ASTNode value= parseValue();
            list.add(value);
            if(lexer.peekNext() == ',') {
                lexer.peekNext();
            }
        }
        lexer.peekNext();
        return new ArrayNode(list);
    }


}
