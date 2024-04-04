package org.example;

import java.io.IOException;
import java.io.InputStreamReader;

//We will run pico-cli from this class
public class JSON {
    public static void main(String[] args) throws IOException {
        JSONParser parser= new JSONParser(new InputStreamReader(System.in));
        parser.parse();
        System.out.println("Successful parsing!");
    }
}
