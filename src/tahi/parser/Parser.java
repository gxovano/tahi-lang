package tahi.parser;

import java.util.List;
import java.util.Stack;

import tahi.scanner.Token;

public class Parser {
    
    private MaquinaEstado estado;
    private Stack pilhaTokens;

    public Parser() {
        this.estado = new MaquinaEstado();
        this.pilhaTokens = new Stack<Token>();
    }
    
    
}
