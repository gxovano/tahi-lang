package tahi.parser;

public class Operador implements Elemento {
    private Elemento operando1;
    private Elemento operando2;
    private Character operador;

    public Operador(Character opo, Elemento opr1,Elemento opr2) {
        this.operador = opo;                // TODO Garantir que o operador esteja na lista da classe tahi.scanner.MaquinaEstado.operadores
        this.operando1 = opr1;
        this.operando2 = opr2;
    }

    private void executar() {
        
    }

    public Elemento resolver() {
        return null;
    }
}
