package computador.componentes;

import java.util.ArrayList;

/**
 *
 * 
 */
public class PalavraControle {
    
    private boolean jumpEntradaP1;
    private boolean jumpSaidaP1;
    private boolean jumpSaidaP2;
    
    private ArrayList<Boolean> sinaisDeControle;
    
    private int codigoOperacaoULA;
    private int codigoOperacaoRAM;
    
    private boolean jumpIncondicional;
    private boolean jumpZero;
    private boolean jumpNegativo;
    private boolean jumpPositivo;
    private boolean jumpOverflow;
    
    private boolean lerIR;
    private int enderecoJump;
    
    // DEBUGG
    public int v(boolean a) {
        if(a)
            return 1;
        else
            return 0;
    }
    
    public void imprimir() {
        System.out.print(v(jumpEntradaP1) + " ");
        System.out.print(v(jumpSaidaP1) + " ");
        System.out.print(v(jumpSaidaP2) + "   ");
        
        sinaisDeControle.stream().forEach((sinal) -> {
            System.out.print(v(sinal) + " ");
        });
        
        System.out.print("  " + codigoOperacaoULA + "   " + codigoOperacaoRAM + "   ");
        
        System.out.print(v(jumpIncondicional) + " ");
        System.out.print(v(jumpZero) + " ");
        System.out.print(v(jumpNegativo) + " ");
        System.out.print(v(jumpPositivo) + " ");
        //System.out.print(v(jumpOverflow) + "   ");
        
        System.out.print(v(lerIR) + "   ");
        
        System.out.println(enderecoJump);
    }
    
    public PalavraControle(int numeroSinaisControle) {
        this.sinaisDeControle = new ArrayList(numeroSinaisControle);
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
    
    public void sinalDeControle(int indice, boolean status) {
        this.sinaisDeControle.add(indice, status);
    }
    
    /**
     * Retorna o sinal de controle para uma porta
     * 
     * @param indice Indice do sinal de controle
     * @return Valor booleano do sinal de controle
     */
    public boolean sinalDeControle(int indice) {        
        return this.sinaisDeControle.get(indice);
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
     * Setter para a flag de jump negativo
     * 
     * @param status Valor booleano da flag
     */
    public void jumpNegativo(boolean status) {
        this.jumpNegativo = status;
    }
    
    /**
     * Getter para a flag de jump negativo
     * 
     * @return Valor booleano da flag
     */
    public boolean jumpNegativo() {
        return this.jumpNegativo;
    }
    
    /**
     * Setter para a flag de jump positivo
     * 
     * @param status Valor booleano da flag
     */
    public void jumpPositivo(boolean status) {
        this.jumpPositivo = status;
    }
    
    /**
     * Getter para a flag de jump positivo
     * 
     * @return Valor booleano da flag
     */
    public boolean jumpPositivo() {
        return this.jumpPositivo;
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