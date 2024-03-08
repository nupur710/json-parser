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
                number += number * 10;
                i++;
                continue;
            }
            if (number != 0) {
                tokens.add(new Token(TokenTypes.NUMERIC_LITERAL, String.valueOf(number)));
            }
            if (c == '"') {
                int x = 0;
                i++;
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





















//package org.example;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Scanner;
//
///***
// * Tokenize json string input. Worst case time complexity is
// * O(n*2); needs to be improved
// */
//public class Lexer {
//
//
//
//    public void lookahead() {
//        BufferedReader br= new BufferedReader(new FileReader())
//    }
//
//
//    private ArrayList tokenize(String userInput) {
//        int constructNo= 0;
//        ArrayList<String> token= new ArrayList<>();
//        var builder= new StringBuilder();
//        var x= new StringBuilder();
//        char v;
//        char m= 0;
//        for(int i= 0; i<userInput.length(); i++) {
//            v= userInput.charAt(i);
//            if(userInput.charAt(i) == '{')
//                token.add(String.valueOf(userInput.charAt(i)));
//            if(userInput.charAt(i) == ':' || userInput.charAt(i) == ',')
//                token.add(String.valueOf(userInput.charAt(i)));
//            if(userInput.charAt(i) == '"') {
//                token.add(String.valueOf(userInput.charAt(i)));
//                for(int j= i+1; j<userInput.length(); j++) {
//                    i= j;
//                    if(userInput.charAt(j) == '"') {
//                        m= userInput.charAt(j);
//                        break;
//                    }
//
//                    x.append(userInput.charAt(j));
//                }
//                if(x!= null || !x.equals("")) {
//                    token.add(String.valueOf(x));
//                    x.setLength(0);
//                    token.add(String.valueOf(m));
//                }
//            }
//
//            if(Character.isDigit(userInput.charAt(i))) {
//                constructNo= constructNo*10 + Character.getNumericValue(userInput.charAt(i));
//                continue;
//            }
//            if(constructNo != 0) {
//                token.add(String.valueOf(constructNo));
//                constructNo = 0;
//            }
//
//            if(userInput.charAt(i) == '}') {
//                token.add(String.valueOf(userInput.charAt(i)));
//            }
//        }
//        return token;
//    }
//
//
//    public static void main(String[] args) {
//        Scanner sc= new Scanner(System.in);
//        String userInput= sc.nextLine();
//        Lexer lexer= new Lexer();
//        lexer.tokenize(userInput);
//    }
//
//
//}