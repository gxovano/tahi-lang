package tahi.parser;

import java.util.Set;
import java.util.List;
import java.util.Stack;

import tahi.runtime.Variavel;
import tahi.scanner.Token;

public class Parser {
    
    Stack<Token> raw;
    Stack<Token> operadores;
    Stack<Token> variaveis;
    Stack<Estado> tipo;

    MaquinaEstado estado;

    public Parser() {
        this.raw = new Stack<Token>();
        this.operadores = new Stack<Token>();
        this.variaveis = new Stack<Token>();
        this.tipo = new Stack<Estado>();
        this.estado = new MaquinaEstado();
    }

    public void parseLine(List<Token> linha) throws Exception {

        for(Token t : linha) {
            switch (t.getClasse()) {
                case Keyword:
                    //TODO tratamento de keywords
                break;
                case Variavel:
                    this.variaveis.push((Token) t);
                    this.tipo.push(Estado.Var);
                break;
                case Inteiro:
                    this.raw.push((Token) t);
                    this.tipo.push(Estado.Raw);
                break;
                case Flutuante:
                    this.raw.push((Token) t);
                    this.tipo.push(Estado.Raw);
                break;
                case Caracter: 
                    // TODO tratamento de caracteres
                break;
                case String:
                    this.raw.push((Token) t);
                    this.tipo.push(Estado.Raw);
                break;
                case Operador:
                    this.operadores.push((Token) t);
                    this.tipo.push(Estado.Operador);
                break;
                default:
                break;
            }
        }
    }      

    public String[] runLine(Set<String> listaVariaveisRuntime) {
        
        return null;
    }
    
}
