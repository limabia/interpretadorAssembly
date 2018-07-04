package computador.componentes;

/**
 *
 * 
 */
public class PalavraControle {
    
    private boolean jumpEntradaP1;
    private boolean jumpSaidaP1;
    private boolean jumpSaidaP2;
    
    private boolean[] sinaisDeControle;
    
    private int codigoOperacaoULA;
    private int codigoOperacaoRAM;
    
    private boolean jumpIncondicional;
    private boolean jumpZero;
    private boolean jumpOverflow;
    
    private boolean lerIR;
    private int enderecoJump;
    
    // DEBUGG
    public void imprimir() {
        
    }
    
    //DOCUMENTAR ISSO DPS
    
    public void jumpEntradaP1(boolean status) {
        this.jumpEntradaP1 = status;
    }
    
    public boolean jumpEntradaP1() {
        return this.jumpEntradaP1;
    }
    
    public void jumpSaidaP1(boolean status) {
        this.jumpSaidaP1 = status;
    }
    
    public boolean jumpSaidaP1() {
        return this.jumpSaidaP1;
    }
    
    public void jumpSaidaP2(boolean status) {
        this.jumpSaidaP2 = status;
    }
    
    public boolean jumpSaidaP2() {
        return this.jumpSaidaP2;
    }
    
    /**
     * Define um conjunto de sinais de controle booleanas
     * 
     * @param sinaisDeControle Sinais de controle
     */
    public void sinaisDeControle(boolean[] sinaisDeControle) {
        this.sinaisDeControle = sinaisDeControle;
    }
    
    public void sinalDeControle(int indice, boolean status) {
        if(indice < 0 || indice >= this.sinaisDeControle.length)
            throw new IllegalArgumentException("Indice invalido");
        
        this.sinaisDeControle[indice] = status;
    }
    
    /**
     * Retorna o sinal de controle para uma porta
     * 
     * @param indice Indice do sinal de controle
     * @return Valor booleano do sinal de controle
     */
    public boolean sinalDeControle(int indice) {
        if(indice < 0 || indice >= this.sinaisDeControle.length)
            throw new IllegalArgumentException("Indice invalido");
        
        return this.sinaisDeControle[indice];
    }
    
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
    public void jumpIncondicional(boolean status) {
        this.jumpIncondicional = status;
    }
    
    /**
     * Getter para a flag de jump incondicional
     * 
     * @return Valor booleano da flag
     */
    public boolean jumpIncondicional() {
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
    
    /**
     * Setter para a flag de leitura do enndereco no IR
     * 
     * @param status Valor boolean da flag
     */
    public void lerIR(boolean status) {
        this.lerIR = status;
    }
    
    /**
     * Getter para a flag da leitura do proximo endereco
     * 
     * @return 'true' caso seja necessario ler do IR o proximo endereco, 'false'
     *         caso nao seja
     */
    public boolean lerIR() {
        return this.lerIR;
    }

    /**
     * Setter para o endereco para a proxima instrucao em caso de jump
     * 
     * @param enderecoJump Endreco de jump no microprograma
     */
    public void enderecoJump(int enderecoJump) {
        this.enderecoJump = enderecoJump;
    }
    
    /**
     * Getter para o endereco de jump
     * 
     * @return Endreco de jump no microprograma
     */
    public int enderecoJump() {
        return this.enderecoJump;
    }
}