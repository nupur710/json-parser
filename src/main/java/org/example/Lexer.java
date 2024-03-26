package org.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lexer {

    private InputStreamReader reader;
    private Queue<Token> readChars;

    public Lexer(InputStreamReader reader) {
        this.reader= reader;
        this.readChars= new LinkedList<Token>();
    }

    public Token tokenizer() throws IOException {
       Character ch= this.peekNext();
        if(ch == null) readChars.add(new Token(TokenTypes.EOF, "EOF"));
        switch (ch) {
            case ' ', '\n','\r','\t' -> {
                handleWhitespace(ch);
            } case '{' -> {
                return new Token(TokenTypes.LEFT_CURLY_BRACKET, "{");
            } case '}' -> {
                return new Token(TokenTypes.RIGHT_CURLY_BRACKET, "}");
            } case ':' -> {
                return new Token(TokenTypes.SEMICOLON, ":");
            } case ',' -> {
                return new Token(TokenTypes.COMMA, ",");
            } case '[' -> {
                return new Token(TokenTypes.OPEN_ARRAY, "[");
            } case ']' -> {
                return new Token(TokenTypes.CLOSE_ARRAY, "]");
            } case '"' -> {
                String a= constructString();
                return new Token(TokenTypes.STRING_LITERAL, a);
            } case '0','1','2','3','4','5','6','7','8','9' -> {
                String number= constructNumber(ch);
                System.out.println(number);
            }
            default -> throw new IOException("Invalid character");
        }
        return null;
    }

    private String constructNumber(Character ch) throws IOException {
        var number= new StringBuilder();
        number.append(ch);
        char x= this.peekNext();
        while(Character.isDigit(x)) {
            number.append(x);
            x= this.peekNext();
        }
        return number.toString();
    }

    private String constructString() throws IOException {
        var string= new StringBuilder();
        char x= this.peekNext();
        while((x)!='"') {
            string.append(x);
            x= this.peekNext();
        }
        return string.toString();
    }

    private void handleWhitespace(Character ch) throws IOException {
        while(ch != '\n' || ch != '\r' ||ch != '\t' || ch != ' ') {
            this.peekNext();
        }
    };

//    public Queue<Token> tokenizer(String input) {
//        Queue<Token> tokens = new LinkedList<>();
//        int i = 0, number = 0;
//        StringBuilder stringBuilder = null;
//        while (i < input.length()) {
//            char c = input.charAt(i);
//            if(Character.isWhitespace(c)) {
//                i++;
//                continue;
//            }
//            if (c == '{') {
//                tokens.add(new Token(TokenTypes.LEFT_CURLY_BRACKET, "{"));
//                i++;
//                continue;
//            }
//            if (c == '}') {
//                tokens.add(new Token(TokenTypes.RIGHT_CURLY_BRACKET, "}"));
//                i++;
//                continue;
//            }
//            if (c == ':') {
//                tokens.add(new Token(TokenTypes.SEMICOLON, ":"));
//                i++;
//                continue;
//            }
//            if (c == ',') {
//                tokens.add(new Token(TokenTypes.COMMA, ","));
//                i++;
//                continue;
//            }
//            if (Character.isDigit(c)) {
//                number= number*10 + Character.getNumericValue(c);
//                i++;
//                if (Character.isDigit(input.charAt(i)))  { //we cannot use Character.isDigit(c+1) here because that appends 1 to c --> if c is 2 --> c+1= 21
//                    continue;
//                }
//                else {
//                    tokens.add(new Token(TokenTypes.NUMERIC_LITERAL, String.valueOf(number)));
//                    number= 0;
//                    continue;
//                }
//            }
//            if (c == '"') {
//                i++;
//                int x= i;
//                while ((input).charAt(i) != '"') {
////logic1                    stringBuilder.append(c);
//                    i++; //logic2
//                }
//
//                String construct_string = input.substring(x, i); //logic2
//                tokens.add(new Token(TokenTypes.STRING_LITERAL, construct_string));
//                i++;
//                continue;
//                //logic1               tokens.add(new Token(TokenTypes.STRING_LITERAL, stringBuilder.toString()));
//            }
//            if (c == 'f' && input.charAt(i + 1) == 'a' && input.charAt(i + 2) == 'l' && input
//                    .charAt(i + 3) == 's' && input.charAt(i+4) == 'e') {
//                tokens.add(new Token(TokenTypes.TRUE, "false"));
//                i += 5;
//                continue;
//            }
//            if (c == 't' && input.charAt(i + 1) == 'r' && input.charAt(i + 2) == 'u' && input
//                    .charAt(i + 3) == 'e') {
//                tokens.add(new Token(TokenTypes.TRUE, "true"));
//                i += 4;
//                continue;
//            }
//            if (c == 'n' && input.charAt(i + 1) == 'u' && input.charAt(i + 2) == 'l' && input
//                    .charAt(i + 3) == 'l') {
//                tokens.add(new Token(TokenTypes.TRUE, "null"));
//                i += 4;
//                continue;
//            }
//
//            else {
//                //throw error
//            }
//        } return tokens;
//    }

    public Character peekNext() throws IOException {
        char ch= (char) reader.read();
        return ch;
    }

    public static void main(String[] args) throws IOException {
//        Scanner sc= new Scanner(System.in);
//        String ip= sc.nextLine();
        InputStreamReader streamReader= new InputStreamReader(System.in);
        Lexer lexer= new Lexer(streamReader);
        lexer.tokenizer();
//        Queue<Token> t= lexer.tokenizer(ip);
//        for(Token to : t) {
//            System.out.println(to.getValue());
//        }
    }
}