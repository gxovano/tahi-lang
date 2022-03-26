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

import tahi.runtime.Runtime;

public class Interpretador {
    public static void main(String[] args) {
        if (args.length == 0) {
            // TODO Programa sem o nome do arquivo fonte
            System.exit(0);
        }
        try{
            Runtime rt = new Runtime(args[0]);   
            rt.run();         
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.exit(0);
    }
}