package org.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class JSONParser {

    private String input;
    private int position;
    private Lexer lexer;

    public JSONParser(String input, InputStreamReader inputStreamReader) {
        this.input= input;
        this.position= 0;
        this.lexer= new Lexer(inputStreamReader);
    }

    public ASTNode parse() throws IOException {
        return parseObject();
    }

    private ASTNode parseObject() throws IOException {
        if(input.charAt(position) != '{') { //first character is not opening bracket
            //throw exception
        } position++;
        Map<String, ASTNode> keyValue= new HashMap<>();
        while(input.charAt(position) != '}') {
            String key= lexer.constructString();
            if(input.charAt(position) != ':') {
                //throw error
            } position++;
            ASTNode value= parseValue();
            keyValue.put(key, value);
            if(input.charAt(position) == ',') {
                position++;
            }
        }
        position++;
        return new ObjectNode(keyValue);
    }


}
