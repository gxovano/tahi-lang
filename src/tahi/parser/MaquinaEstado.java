package tahi.parser;

import tahi.scanner.Token;

public class MaquinaEstado {
    private Estado estado;

    public MaquinaEstado() {
        this.estado = Estado.Inicial;
    }

    public void transicao(Token t) {
	switch (this.estado) {
	  case Inicial:
		this.transicaoInicial(t);
	  break;
	  default:
	  break;	
	}
    }

    private void transicaoInicial(Token t) {

    }


    public void reset() {
	this.estado = Estado.Inicial;
    }
}
