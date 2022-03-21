package tahi.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Stack;
import java.lang.Exception;

public class Tokenizer {
  private MaquinaEstado estado;
  private Stack pilhaCaracteres;

  public Tokenizer() {
    this.estado = new MaquinaEstado();
    this.pilhaCaracteres = new Stack<Character>();
  }

  public List<List<Token>> tokenizer(List<String> source) throws Exception{
    Iterator it = source.iterator();
    List<List<Token>> s = new ArrayList<List<Token>>();
    int indiceLinha = 0;
    try {
      while(it.hasNext()) {
        indiceLinha++;
        String str = (String) it.next();
        s.add(processar((String) str.replace("\n","")));
      }
    }
    catch(Exception e) {
      throw new Exception("Erro na linha " + indiceLinha + e.getMessage() +"\nFalha na expressao \"" + source.get(--indiceLinha) + "\"");
    }
    return s;
  }

  /**
   * assss
   *
   * @param instrucao 
   * @return 
   */
  private List<Token> processar(String instrucao) throws Exception{
    List<Token> lista = new ArrayList<Token>();
    for(int i = 0; i < instrucao.length(); i++) {
      char c = instrucao.charAt(i);
      estado.transicao(c);
      if (estado.ehValido() && !estado.ehFinal()) {
        this.pilhaCaracteres.push((Character) c);
      }
      else if (estado.ehFinal()) {
        if(c == '"')
          this.pilhaCaracteres.push((Character) c);
        Token t = reconhecer();
        if(t != null) {
          lista.add(t);
        }
        if(!Character.isWhitespace(c) && c != '"') {
          i--;
        }
        this.estado.reset();
      }
      else if (!estado.ehValido()) {
        throw new Exception(" coluna " + i);
      }
    }
    lista.add(reconhecer());
    this.estado.reset();
    return lista;
  }

  private Token reconhecer() {
    Token t = new Token();
    t.setConteudo(popAll());
    t.definirToken(this.estado.getEstadoFinal());
    return t.getConteudo() != " " ? t : null;
  }

  private String popAll() {
    StringBuilder s = new StringBuilder(this.pilhaCaracteres.size());
    while (!this.pilhaCaracteres.empty())
      s.append((Character) this.pilhaCaracteres.pop());
    return s.reverse().toString();
  }
}
