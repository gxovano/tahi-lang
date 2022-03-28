package tahi.runtime;

import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.Charset;

import tahi.parser.Parser;
import tahi.scanner.Token;
import tahi.scanner.Tokenizer;


public class Runtime {
    private String caminhoFonte = null;
    private List<String> source = null;

    private Map<String,Variavel> variaveisRuntime= null;

    public Runtime() {}
    public Runtime(String fonte) throws Exception{
        if(!this.checkFile(fonte) || fonte.isEmpty())
            throw new Exception("O arquivo fonte não foi passado como parâmetro ou não existe.");
        else {
            this.caminhoFonte = fonte;
            this.source = Files.readAllLines(new File(fonte).toPath(), Charset.defaultCharset() );
            this.variaveisRuntime = new HashMap<String,Variavel>();
        }
    }

    public void run() throws Exception {
        Tokenizer tok = new Tokenizer();
        Parser par = new Parser();
        List<List<Token>> lista = tok.tokenizer(this.source);
        // Para proposito de debug, print do resultado do scanner
        for(List<Token> lista2 : lista){
            for (Token t : lista2) {
                System.out.print(t.getClasse());
                System.out.print(" " + t.getConteudo().toString());
                System.out.print(",");
            }
            System.out.print("\n");
        }
        // Aplicação do parser 
        for(List<Token> lista2 : lista){
            par.parseLine(lista2);
            String[] result = par.runLine(this.variaveisRuntime.keySet());
            if (result != null) {

            }
        }

    }

    private boolean checkFile(String file){
        File source = new File(file);
        if(source.exists() && source.isFile() && (file.lastIndexOf(".tahi") > 0)) 
            return true;
        else
            return false;
    }
}
