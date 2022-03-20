package tahi.scanner;

public class Token {

  private String conteudo;
  private Primitivo classe;
  private final String keywords = "print,";

  public Token() {
    this.conteudo = "";
    this.classe = null;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }

  public String getConteudo() {
    return this.conteudo;
  }

  public Primitivo getClasse() {
    return this.classe;
  }

  public void definirToken(Estado[] estadoFinal) {
    Estado avaliado;
    if(estadoFinal[0] == Estado.Inicial)
      avaliado = estadoFinal[1];
    else
      avaliado = estadoFinal[0];

    if(avaliado == Estado.Numerico) {
      this.classe = Primitivo.Inteiro; //TODO implementar tratamento flutuante
    }
    else if(avaliado == Estado.Flutuante) {
      this.classe = Primitivo.Flutuante;
    }
    else if (avaliado == Estado.Operador) {
      this.classe = Primitivo.Operador;
    }
    else if(avaliado == Estado.String) {
      this.classe = Primitivo.String;
    }
    else if (avaliado == Estado.Alfabetico) {
      if(this.keywords.contains(this.getConteudo()))
        this.classe = Primitivo.Keyword;
      else
        this.classe = Primitivo.Variavel;
    }
  }
}
