package tahi.parser;

/**
 * Interface representando cada elemento em uma linha de comando.
 * 
 * @author gxovano geovano.quatrin@gmail.com
 */
public abstract interface Elemento {
    /**
     * 
     * @return
     */
    public Elemento resolver() throws Exception;
}
