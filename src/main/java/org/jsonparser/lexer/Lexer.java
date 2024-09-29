package org.jsonparser.lexer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lexer {

    private InputStreamReader reader;
    public Queue<Character> buffer;

    public Lexer(InputStreamReader reader) {
        this.reader = reader;
        this.buffer = new LinkedList<Character>();
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
//            case ' ', '\n', '\r', '\t' -> {
//                ch= handleWhitespace(ch);
//            }
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
        char l = 0;
        var number = new StringBuilder();
        number.append(ch);
        char x = this.peekNext();
        while (Character.isDigit(x)) {
            number.append(x);
            x = this.peekNext();
            l=x;
        }
//        char c= toString().charAt(0);
//        if(c)
        int n= Integer.parseInt(number.toString());
         if(n >= 0 && n <= 9) {
             buffer.add(x);
         } else {
             buffer.add(l);
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
            ch= this.peekNext();
        } return ch;
    }

    public Character peekNext() throws IOException { //consumes
        if(!buffer.isEmpty()) {
            return buffer.poll();
        } else {
            char ch = (char) reader.read();
            if(ch == -1) {
                return null;
            }
            buffer.add(ch);
            return buffer.poll();
        }
    }
}