package tahi;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.nio.file.Files;
import java.nio.charset.Charset;

import tahi.scanner.Tokenizer;
import tahi.scanner.Token;

public class Interpretador {
    public static void main(String[] args) {
        if (args.length == 0) {
            // TODO Programa sem o nome do arquivo fonte
            System.exit(0);
        }
        try{
            List lista = new ArrayList<List<Token>>();
            Tokenizer tok = new Tokenizer();
            List<String> list = Files.readAllLines(new File(args[0]).toPath(), Charset.defaultCharset() );
            lista = tok.tokenizer(list);
            Iterator it = lista.iterator();
            while(it.hasNext()) {
                List<Token> lista2 = (List<Token>) it.next();
                for (Token t : lista2) {
                    System.out.print(t.getClasse());
                    System.out.println(" " + t.getConteudo().toString());
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.exit(0);
    }
}