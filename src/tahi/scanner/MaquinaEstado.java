package tahi.scanner;

import java.lang.Character;

public class MaquinaEstado {

  private Estado[] estado;
  private final String operadores = "=+-*/";
  private final String add = "++";
  private final String minus = "--";
  private char previous = ' ';

  public MaquinaEstado() {
    this.estado = new Estado[] {Estado.Inicial,Estado.Inicial};
  }

  private void shiftEstado(Estado novo) {
    Estado s;
    s = this.estado[1];
    this.estado[1] = novo;
    this.estado[0] = s;
  }

  private Estado transicaoInicial(char caracter) {
    if(Character.isLetter(caracter))
      return Estado.Alfabetico;
    else if (Character.isDigit(caracter))
      return Estado.Numerico;
    else if(this.isOperator(caracter))
      return Estado.Operador;
    else if(Character.isWhitespace(caracter))
      return Estado.Final;
    else if(caracter == '"')
      return Estado.String;
    else
      return Estado.Invalido;
  }

  private Estado transicaoAlfabetico(char caracter) {
    if(Character.isDigit(caracter) || Character.isLetter(caracter))
      return Estado.Alfabetico;
    else
      return Estado.Final;
  }

  private Estado transicaoNumerico(char caracter) {
    if(Character.isDigit(caracter))
      return Estado.Numerico;
    else if(caracter == '.')
      return Estado.Flutuante;
    else
      return Estado.Final;
  }

  private Estado transicaoFlutuante(char caracter) {
    if(Character.isDigit(caracter))
      return Estado.Flutuante;
    else if(caracter == '.')
      return Estado.Invalido;
    else
      return Estado.Final;
  }

  private Estado transicaoString(char caracter) {
    if(caracter == '"')
      return Estado.Final;
    else
      return Estado.String;
  }

  private Estado transicaoOperador(char caracter) {
    if(caracter == this.previous) {
      if((this.add.indexOf(caracter) != -1 || this.add.indexOf(caracter) != -1) && this.estado[1] == Estado.Operador)
        return Estado.Final;
    }
    else
      return Estado.Final;
    return Estado.Invalido;
  }

  private boolean isOperator(char caracter) {
    return this.operadores.indexOf(caracter) > -1;
  }

  public Estado transicao(char caracter){
    switch (this.estado[1]) {
      case Inicial:
        shiftEstado(transicaoInicial(caracter));
        break;
      case Alfabetico:
        shiftEstado(transicaoAlfabetico(caracter));
        break;
      case Numerico:
        shiftEstado(transicaoNumerico(caracter));
        break;
      case Flutuante:
        shiftEstado(transicaoFlutuante(caracter));
        break;
      case Operador:
        shiftEstado(transicaoOperador(caracter));
        break;
      case String:
        shiftEstado(transicaoString(caracter));
      default:
        break;
    }
    this.previous = caracter;
    return this.estado[1];
  }

  public boolean ehValido() {
    return this.estado[1] != Estado.Invalido;
  }

  public boolean ehFinal() {
    return this.estado[1] == Estado.Final;
  }

  public void reset() {
    this.estado[0] = Estado.Inicial;
    this.estado[1] = Estado.Inicial;
  }

  public Estado[] getEstadoFinal() {
    return this.estado;
  }
}
