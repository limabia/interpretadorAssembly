package computador.componentes;

/**
 *
 * @author a10414571
 */
public class PalavraControle {
    
    private boolean[] sinaisDeControle;
    
    private int codigoOperacaoULA;
    private int codigoOperacaoRAM;
    
    private boolean jumpIncondicional;
    private boolean jumpZero;
    private boolean jumpOverflow;
    
    
    /**
     * Setter para a codigo da operacao da ULA
     * 
     * @param codigoOperacao Codigo da operacao da ULA
     */
    public void operacaoULA(int codigoOperacao) {
        if(!ULA.codigoValido(codigoOperacao))
            return;
        
        this.codigoOperacaoULA = codigoOperacao;
    }
    
    /**
     * Getter para o codigo da operacao da ULA
     * 
     * @return Codigo da Operacao da ULA
     */
    public int operacaoULA() {
        return this.codigoOperacaoULA;
    }
    
    /**
     * Setter para a codigo da operacao da RAM
     * 
     * @param codigoOperacao Codigo da operacao da RAM
     */
    public void operacaoRAM(int codigoOperacao) {
        if(!RAM.codigoValido(codigoOperacao))
            return;
        
        this.codigoOperacaoRAM = codigoOperacao;
    }
    
    /**
     * Getter para o codigo da operacao da RAM
     * 
     * @return Codigo da Operacao da RAM
     */
    public int operacaoRAM() {
        return this.codigoOperacaoRAM;
    }
    
    /**
     * Setter para a flag de jump incondicional
     * 
     * @param status Valor boolean da flag
     */
    public void jumpIndondicional(boolean status) {
        this.jumpIncondicional = status;
    }
    
    /**
     * Getter para a flag de jump incondicional
     * 
     * @return Valor booleano da flag
     */
    public boolean jumpIndondicional() {
        return this.jumpIncondicional;
    }
       
    /**
     * Setter para a flag de jump zero
     * 
     * @param status Valor boolean da flag
     */
    public void jumpZero(boolean status) {
        this.jumpZero = status;
    }
    
    /**
     * Getter para a flag de jump zero
     * 
     * @return Valor booleano da flag
     */
    public boolean jumpZero() {
        return this.jumpZero;
    }
    
    /**
     * Setter para a flag de jump overflow
     * 
     * @param status Valor boolean da flag
     */
    public void jumpOverflow(boolean status) {
        this.jumpOverflow = status;
    }
    
    /**
     * Getter para a flag de jump overflow
     * 
     * @return Valor booleano da flag
     */
    public boolean jumpOverflow() {
        return this.jumpOverflow;
    }
}
