package tahi.runtime;

import java.util.List;
import java.util.Iterator;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.Charset;

import tahi.scanner.Token;
import tahi.scanner.Tokenizer;


public class Runtime {
    private String caminhoFonte = null;
    private List<String> source = null;

    public Runtime() {}
    public Runtime(String fonte) throws Exception{
        if(!this.checkFile(fonte) || fonte.isEmpty())
            throw new Exception("O arquivo fonte não foi passado como parâmetro ou não existe.");
        else {
            this.caminhoFonte = fonte;
            this.source = Files.readAllLines(new File(fonte).toPath(), Charset.defaultCharset() );
        }
    }

    public void run() throws Exception{
        Tokenizer tok = new Tokenizer();
        List lista = tok.tokenizer(this.source);
        Iterator it = lista.iterator();
        while(it.hasNext()) {
            List<Token> lista2 = (List<Token>) it.next();
            System.out.print("\n");
            for (Token t : lista2) {
                System.out.print(t.getClasse());
                System.out.print(" " + t.getConteudo().toString());
                System.out.print(",");
            }
            System.out.print("\n");
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
