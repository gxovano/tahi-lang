package tahi.parser;

import java.util.Set;
import java.util.List;
import java.util.Stack;

import tahi.runtime.Variavel;
import tahi.scanner.Token;

public class Parser {
    
    Stack<Token> raw;
    MaquinaEstado estado;

    public Parser() {
        this.raw = new Stack<Token>();
        this.estado = new MaquinaEstado();
    }

    public void parseLine(List<Token> linha) throws Exception {

        for(Token t : linha) {
		this.raw.push(t)
        }
    }      

    public String[] runLine(Set<String> listaVariaveisRuntime) {
        
        return null;
    }
    
}
