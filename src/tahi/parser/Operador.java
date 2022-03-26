package tahi.parser;

public class Operador implements Elemento {
    private Variavel operando1;
    private Variavel operando2;
    private Character operador;
    private Tipo resultante = null;

    public Operador(Character opo, Variavel opr1,Variavel opr2) {
        this.operador = opo;                // TODO Garantir que o operador esteja na lista da classe tahi.scanner.MaquinaEstado.operadores
        this.operando1 = opr1;
        this.operando2 = opr2;
    }

    public Elemento resolver() throws Exception{
        return new Variavel(executar(),this.resultante);
    }

    private String executar() throws Exception{
        String resultado = null;
        switch (this.operador) {
            case '+':
                if (this.testeLogicoSoma()) {
                    resultado = executaSoma();
                }
                else 
                    throw erroTipoIncompativel();
                break;
            case '-':
                if (this.testeLogicoSubtracao()) {
                    resultado = null;
                }
                else 
                    throw erroTipoIncompativel();
                break;
            case '=':
                break;
                //TODO executar os demais 
            default:
                break;
        }
        return resultado;
    }

    private boolean testeLogicoSoma() {

        if (this.operando1.isFlutuante() && (this.operando2.isFlutuante() || this.operando2.isInteiro()) || 
        (this.operando2.isFlutuante() && (this.operando1.isFlutuante() || this.operando1.isInteiro()))) {
            this.resultante = Tipo.Flutuante;
            return true;
        }
        if (this.operando1.isInteiro() && this.operando2.isInteiro()) {
            this.resultante = Tipo.Inteiro;
            return true;
        }
        if (this.operando1.isString() && this.operando2.isString()) {
            this.resultante = Tipo.String;
            return true;
        }

        return false;
    }

    private boolean testeLogicoSubtracao() {
        if (this.operando1.isFlutuante() && (this.operando2.isFlutuante() || this.operando2.isInteiro()) || 
        (this.operando2.isFlutuante() && (this.operando1.isFlutuante() || this.operando1.isInteiro()))) {
            return true;
        }
        if (this.operando1.isInteiro() && this.operando2.isInteiro()) {
            return true;
        }

        return false;
    }

    private Exception erroTipoIncompativel() {
        return new Exception("Os seguintes tipos são incompativeis: " + this.operando1.getTipo() + 
        " " + this.operando2.getTipo());
    }

    private String executaSoma() {
        String resultado = null;
        switch (this.resultante) {
            case Flutuante:
                resultado = Float.toString(Float.parseFloat(this.operando1.getValue()) 
                            + Float.parseFloat(this.operando2.getValue()));
            break;
            case Inteiro:
                resultado = Integer.toString(Integer.parseInt(this.operando1.getValue()) 
                            + Integer.parseInt(this.operando2.getValue()));
            break;
            case String:
                resultado = this.operando1.getValue() + this.operando2.getValue();
            break;
            default:
            break;
        }
        return resultado;
    }
}
