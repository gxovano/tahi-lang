package tahi.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Stack;
import java.lang.Exception;

public class Tokenizer {
  // Instancia maquina de estado de avaliação dos caracteres
  private MaquinaEstado estado;
  // Instancia a pilha de token 
  private Stack pilhaCaracteres;

  public Tokenizer() {
    this.estado = new MaquinaEstado();
    this.pilhaCaracteres = new Stack<Character>();
  }

  public List<List<Token>> tokenizer(List<String> source) throws Exception {
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

  private List<Token> processar(String instrucao) throws Exception {
    List<Token> lista = new ArrayList<Token>();               // Inicializa a lista de tokens da linha do fonte
    for(int i = 0; i < instrucao.length(); i++) {             // Para cada caracter da linha, executa as instruções do laço
      char c = instrucao.charAt(i);                           // -- Lê o caracter da vez na variável c
      estado.transicao(c);                                    // -- Produz uma transição de estado na máquina de estado com base no caracter
      if (estado.ehValido() && !estado.ehFinal()) {           // -- Caso o estado seja válido ou final
        this.pilhaCaracteres.push((Character) c);             // -- -- Coloca o caracter na pilha de caracteres de token
      }
      else if (estado.ehFinal()) {                            // -- Caso o estado seja final (o token está completo)
        if(c == '"')                                          // -- -- Se o último caracter for aspas duplas
          this.pilhaCaracteres.push((Character) c);           // -- -- -- Coloca as aspas duplas na pilha de caracteres
        Token t = reconhecer();                               // -- -- Cria um objeto novo do tipo Token com base no resultado da máquina de estado
        if(t != null) {                                       // -- -- Caso o token não seja inválido (null)
          if(t.getClasse() != null && t.getConteudo() != null) // 
            if(t.getClasse() != null)
              lista.add(t);                                     // -- -- -- Adiciona o token na lista de tokens 
        }
        if(!Character.isWhitespace(c) && c != '"') {          // -- -- Se o caracter 
          i--;                                                // -- -- -- 
        }
        this.estado.reset();                                  // -- -- 
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
