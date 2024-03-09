package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lexer {

    public List<Token> tokenizer(String input) {
        ArrayList<Token> tokens = new ArrayList<>();
        int i = 0, number = 0;
        StringBuilder stringBuilder = null;
        while (i < input.length()) {
            char c = input.charAt(i);
            if(Character.isWhitespace(c)) {
                i++;
                continue;
            }
            if (c == '{') {
                tokens.add(new Token(TokenTypes.LEFT_CURLY_BRACKET, "{"));
                i++;
                continue;
            }
            if (c == '}') {
                tokens.add(new Token(TokenTypes.RIGHT_CURLY_BRACKET, "}"));
                i++;
                continue;
            }
            if (c == ':') {
                tokens.add(new Token(TokenTypes.SEMICOLON, ":"));
                i++;
                continue;
            }
            if (c == ',') {
                tokens.add(new Token(TokenTypes.COMMA, ","));
                i++;
                continue;
            }
            if (Character.isDigit(c)) {
                number= number*10 + Character.getNumericValue(c);
                i++;
                if (Character.isDigit(input.charAt(i)))  { //we cannot use Character.isDigit(c+1) here because that appends 1 to c --> if c is 2 --> c+1= 21
                    continue;
                }
                else {
                    tokens.add(new Token(TokenTypes.NUMERIC_LITERAL, String.valueOf(number)));
                    number= 0;
                    continue;
                }
            }
            if (c == '"') {
                i++;
                int x= i;
                while ((input).charAt(i) != '"') {
//logic1                    stringBuilder.append(c);
                    i++; //logic2
                }

                String construct_string = input.substring(x, i); //logic2
                tokens.add(new Token(TokenTypes.STRING_LITERAL, construct_string));
                i++;
                continue;
                //logic1               tokens.add(new Token(TokenTypes.STRING_LITERAL, stringBuilder.toString()));
            }
            if (c == 'f' && input.charAt(i + 1) == 'a' && input.charAt(i + 2) == 'l' && input
                    .charAt(i + 3) == 's' && input.charAt(4) == 'e') {
                tokens.add(new Token(TokenTypes.TRUE, "false"));
                i += 5;
                continue;
            }
            if (c == 't' && input.charAt(i + 1) == 'r' && input.charAt(i + 2) == 'u' && input
                    .charAt(i + 3) == 'e') {
                tokens.add(new Token(TokenTypes.TRUE, "true"));
                i += 4;
                continue;
            }
            if (c == 'n' && input.charAt(i + 1) == 'u' && input.charAt(i + 2) == 'l' && input
                    .charAt(i + 3) == 'l') {
                tokens.add(new Token(TokenTypes.TRUE, "null"));
                i += 4;
                continue;
            }
        } return tokens;
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String ip= sc.nextLine();
        Lexer lexer= new Lexer();
        List<Token> t= lexer.tokenizer(ip);
        for(Token to : t) {
            System.out.println("val " +to.getValue());
        }
    }
}