package tahi;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

import tahi.scanner.Tokenizer;

public class Interpretador {
    public static void main(String[] args) {
        if (args.length == 0) {
            // TODO Programa sem o nome do arquivo fonte
            System.exit(0);
        }
        try{
            Tokenizer tok = new Tokenizer();
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String currentLine = reader.readLine();
            while(currentLine != null) {

            }
            System.out.println(currentLine);
            reader.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        System.exit(0);
    }
}