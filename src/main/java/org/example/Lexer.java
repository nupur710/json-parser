package org.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lexer {

    private InputStreamReader reader;
    private Queue<Token> readChars;

    public Lexer(InputStreamReader reader) {
        this.reader = reader;
        this.readChars = new LinkedList<Token>();
    }

    public Token getToken() throws IOException {
        return tokenizer();
    }

    public Token tokenizer() throws IOException {
        Character ch = this.peekNext();
        if (ch == null) return new Token(TokenTypes.EOF, "EOF");
        if(ch == ' '|| ch== '\n' || ch== '\r'|| ch== '\t'){
            ch= handleWhitespace(ch);
        }
        switch (ch) {
            case '{' -> {
                return new Token(TokenTypes.LEFT_CURLY_BRACKET, "{");
            }
            case '}' -> {
                return new Token(TokenTypes.RIGHT_CURLY_BRACKET, "}");
            }
            case ':' -> {
                return new Token(TokenTypes.SEMICOLON, ":");
            }
            case ',' -> {
                return new Token(TokenTypes.COMMA, ",");
            }
            case '[' -> {
                return new Token(TokenTypes.OPEN_ARRAY, "[");
            }
            case ']' -> {
                return new Token(TokenTypes.CLOSE_ARRAY, "]");
            }
            case '"' -> {
                String a = constructString();
                return new Token(TokenTypes.STRING_LITERAL, a);
            }
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                String number = constructNumber(ch);
                return new Token(TokenTypes.NUMERIC_LITERAL, number);
            }
            case 't' -> {
                String trueVal = checkTrue();
                return new Token(TokenTypes.BOOLEAN, trueVal);
            }
            case 'f' -> {
                String falseVal = checkFalse();
                return new Token(TokenTypes.BOOLEAN, falseVal);
            }
            case 'n' -> {
                String nullVal = checkNull();
                return new Token(TokenTypes.NULL, nullVal);
            }
            default -> throw new IOException("Invalid character");
        }
    }

     String checkNull() throws IOException {
        if (this.peekNext() == 'u' && this.peekNext() == 'l' && this.peekNext() == 'l') {
            return "null";
        }
        return null;
    }

     String checkFalse() throws IOException {
        if (this.peekNext() == 'a' && this.peekNext() == 'l' && this.peekNext() == 's'
                && this.peekNext() == 'e') {
            return "false";
        }
        return null;
    }

     String checkTrue() throws IOException {
        if (this.peekNext() == 'r' && this.peekNext() == 'u' && this.peekNext() == 'e') {
            return "true";
        }
        return null;
    }

     String constructNumber(Character ch) throws IOException {
        var number = new StringBuilder();
        number.append(ch);
        char x = this.peekNext();
        while (Character.isDigit(x)) {
            number.append(x);
            x = this.peekNext();
        }
        return number.toString();
    }

    String constructString() throws IOException {
        var string = new StringBuilder();
        char x = this.peekNext();
        while ((x) != '"') {
            string.append(x);
            x = this.peekNext();
        }
        return string.toString();
    }

    char handleWhitespace(Character ch) throws IOException {
        while (ch == '\n' || ch == '\r' || ch == '\t' || ch == ' ') {
            return this.peekNext();
        } return ch;
    }

    public Character peekNext() throws IOException {
        char ch = (char) reader.read();
        return ch;
    }

}

//    public static void main(String[] args) throws IOException {
////        Scanner sc= new Scanner(System.in);
////        String ip= sc.nextLine();
//        InputStreamReader streamReader= new InputStreamReader(System.in);
//        Lexer lexer= new Lexer(streamReader);
//        lexer.tokenizer();
////        Queue<Token> t= lexer.tokenizer(ip);
////        for(Token to : t) {
////            System.out.println(to.getValue());
////        }
//    }
//}