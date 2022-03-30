package tahi.parser;

import java.util.Set;

import tahi.scanner.Primitivo;
import tahi.scanner.Token;

public class MaquinaEstado {
    private Estado[] estado;
    private Set<String> variaveis;

    public MaquinaEstado(){
      this.estado = new Estado[] {Estado.Inicial,Estado.Inicial};
    }

    public void transicao(Token t,Set<String> v) throws Exception{

      this.variaveis = v;

      switch (this.estado[1]) {
        case Inicial:
          shiftEstado(transicaoInicial(t));
        break;
        case Var:
          shiftEstado(transicaoVar(t));
        break;
        case Raw:
          shiftEstado(transicaoRaw(t));
        break;
        case Operador:
          shiftEstado(transicaoOperador(t));
        break;
        default:
        break;	
      }
      
      this.variaveis = null;
    }

    private Estado transicaoInicial(Token t) throws Exception{
      if( t.getClasse() == Primitivo.Inteiro || t.getClasse() == Primitivo.Flutuante || t.getClasse() == Primitivo.String )
        return Estado.Raw;
      //if( t.getClasse() == Primitivo.Operador && (this.estado[0] == Estado.Raw || this.estado[0] == Estado.Var))
      //  return Estado.Operador;
      if( t.getClasse() == Primitivo.Variavel && this.variaveis.contains(t.getConteudo())) 
        return Estado.Var;
      else if (!this.variaveis.contains(t.getConteudo()))
        throw new Exception("A variavel \"" + t.getConteudo() + "\" não foi instanciada ainda.");
      return Estado.Invalido;
    }

    private Estado transicaoVar(Token t) {
      if( t.getClasse() == Primitivo.Operador && (this.estado[0] == Estado.Raw || this.estado[0] == Estado.Var) )
        return Estado.Operador;
      //if( t.getClasse() == Primitivo.Inteiro || t.getClasse() == Primitivo.Flutuante || t.getClasse() == Primitivo.String )
      //  return Estado.Raw;
      //if( t.getClasse() == Primitivo.Operador )
      //  return null;
      return Estado.Invalido;
    }

    private Estado transicaoRaw(Token t) {
      if ( t.getClasse() == Primitivo.Operador )
        return Estado.Operador;
      if ( t.getClasse() == Primitivo.Inteiro || t.getClasse() == Primitivo.Flutuante || t.getClasse() == Primitivo.String)
        return Estado.Operador;
      return null;
    }

    private Estado transicaoOperador(Token t) {

      return null;
    }

    private void shiftEstado(Estado novo) {
      Estado s;
      s = this.estado[1];
      this.estado[1] = novo;
      this.estado[0] = s;
    }

    public void reset() {
	    this.estado[0] = Estado.Inicial;
	    this.estado[1] = Estado.Inicial;
    }
}

