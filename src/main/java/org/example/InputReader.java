package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputReader {

    private File file;

    private List<String> readInput(String fileName) {
        file= new File(System.getProperty("user.dir")).toPath().resolve("src").resolve("main").resolve("resources").resolve(fileName).toFile();
        List<String> input= null;
        // read large file
        //but memory inefficient since it is inmemory read
        try {
            input = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }




    public static void main(String[] args) {
        System.out.println(new InputReader().readInput("test.txt"));
    }
}
