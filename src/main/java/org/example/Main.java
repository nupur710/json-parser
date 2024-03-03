package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/***
 * Tokenize json string input. Worst case time complexity is
 * O(n*2); needs to be improved
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String userInput= sc.nextLine();
        int constructNo= 0;
        ArrayList<String> token= new ArrayList<>();
        var builder= new StringBuilder();
        var x= new StringBuilder();
        char v;
        char m= 0;
        for(int i= 0; i<userInput.length(); i++) {
            v= userInput.charAt(i);
            if(userInput.charAt(i) == '{')
                token.add(String.valueOf(userInput.charAt(i)));
            if(userInput.charAt(i) == ':' || userInput.charAt(i) == ',')
                token.add(String.valueOf(userInput.charAt(i)));
            if(userInput.charAt(i) == '"') {
                token.add(String.valueOf(userInput.charAt(i)));
                for(int j= i+1; j<userInput.length(); j++) {
                    i= j;
                    if(userInput.charAt(j) == '"') {
                        m= userInput.charAt(j);
                        break;
                    }

                    x.append(userInput.charAt(j));
                }
                if(x!= null || !x.equals("")) {
                token.add(String.valueOf(x));
                x.setLength(0);
                token.add(String.valueOf(m));
            }
            }

            if(Character.isDigit(userInput.charAt(i))) {
                constructNo= constructNo*10 + Character.getNumericValue(userInput.charAt(i));
                continue;
            }
            if(constructNo != 0) {
                token.add(String.valueOf(constructNo));
                constructNo = 0;
            }

            if(userInput.charAt(i) == '}') {
                token.add(String.valueOf(userInput.charAt(i)));
            }
        }
        System.out.println(token);
    }


}