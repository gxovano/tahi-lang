package tahi.parser;

import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import tahi.runtime.Elemento;
import tahi.runtime.Variavel;
import tahi.runtime.Operador;
import tahi.scanner.Token;

public class Parser {
    
    Stack<Token> raw;
    MaquinaEstado estado;
    Elemento w;

    public Parser() {
        this.raw = new Stack<Token>();
        this.estado = new MaquinaEstado();

    }

    public void parseLine(List<Token> linha,Map<String,Variavel> varRuntime) throws Exception {
        // Cria a pilha de tokens para analise e execução
        for(Token t : linha) {
		    this.raw.push(t);
        }

        while(!estado.ehFinal()) {
            Token t = this.raw.pop();
            Estado es = this.estado.transicao(t,varRuntime);
            if(estado.ehValido()) {
                this.w = this.reconhecer(t,es,varRuntime,this.w);
            }
        }
    }      

    private Elemento reconhecer(Token t, Estado e, Map<String,Variavel> varRuntime, Elemento w) throws Exception{
        switch (e) {
            case Raw:
                if (w.getClass() == Operador.class) {
                    ((Operador)w).fillArgs(t.getConteudo(),t.getClasse().toString());
                }
            break;
            case Var:
            break;
            case Operador:
            break;
            default:
            break;
        }
        return null;
    }

    public String[] runLine(Set<String> listaVariaveisRuntime) {
        
        return null;
    }
    
}
