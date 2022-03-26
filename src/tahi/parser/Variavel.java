package tahi.parser;

public class Variavel implements Elemento{
    private Tipo tipo;
    private String value;

    public Variavel(String v, Tipo t) {
        this.value = v;
        this.tipo = t;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public void setTipo(Tipo t) {
        this.tipo = t;
    }

    public boolean isString() {
        return this.tipo == Tipo.String;
    }

    public boolean isFlutuante() {
        return this.tipo == Tipo.Flutuante;
    }

    public boolean isInteiro() {
        return this.tipo == Tipo.Inteiro;
    }
    
    public Elemento resolver() {
        return this;
    }  

    public String getValue() {
        return this.value;
    }

    public Object getValueLiteral() {
        if(this.tipo == Tipo.Flutuante) {
            return Float.parseFloat(this.value);
        }
        if(this.tipo == Tipo.Inteiro) {
            return Integer.parseInt(this.value);
        }
        if(this.tipo == Tipo.String) {
            return this.value;
        }
        return null;
    }
}
