package computador.componentes;

/**
 *
 * Data: 31-05-2018
 */
public class ULA {
    
    private final int tamanhoOperando;
    
    /* Registradores de entrada e saida */
    private Registrador segundoOperando; // Registrador com o segundo operando
    private Registrador resultado; // Registrador para armazenar o resultado
    
    /* Flags */
    private boolean flagZero; // Resultado da operacao deu igual a zero
    private boolean flagSinalPositivo; // Se resultado eh positivo ou nao
    private boolean flagOverflow; // Operacao resultou em overflow


    
    /**
     * 
     * @param tamanhoOperando
     * @param segundoOperando
     * @param resultado 
     */
    public ULA(int tamanhoOperando, Registrador segundoOperando, Registrador resultado) {
        if(segundoOperando.obterTamanho() != tamanhoOperando || resultado.obterTamanho() != 2 * tamanhoOperando)
            throw new IllegalArgumentException("Nao eh possivel criar a ULA: "
                + "o tamanho dos registradores e da entrada nao estao adequados");
        
        if(resultado.numeroParticoes() != 2)
            throw new IllegalArgumentException("O Registrador de saida deve ter exatamente duas particoes");
        
        if(tamanhoOperando <= 0)
            throw new IllegalArgumentException("O tamanho dos operandos da ULA devem ser maiores do que 0");
        
        this.tamanhoOperando = tamanhoOperando;
        
        this.segundoOperando = segundoOperando;
        this.resultado = resultado;
        
        this.flagZero = false;
        this.flagSinalPositivo = false;
        this.flagOverflow = false;
        
    }
    
    
    
    /**
     * Retorna o valor da flag de resultado zero.
     * 
     * @return 'true' se o ultimo resultado for zero, 'false' caso contrario.
     */
    public boolean obterFlagZero() {
        return this.flagZero;
    }
    
    /**
     * Retorna o valor da flag de sinal positivo.
     * 
     * @return 'true' caso o ultimo resultado tenha sido positivo, 'false' caso
     *         contrario.
     */
    public boolean obterSinalPositivo() {
        return this.flagSinalPositivo;
    }
    
    /**
     * Retorna o valor da flag de overflow.
     * 
     * @return 'true' caso a ultima operacao tenha dado overflow, 'false' caso
     *         contrario.
     */
    public boolean obterFlagOverflow() {
        return this.flagOverflow;
    }
    
    
    
    /**
     * Dado um codigo de operacao e um operando realiza uma operacao com o
     * primeiroOPerando, passado por paramentro, e com o valor armazenado no
     * Registrador segundoParametro (caso seja uma operacao binaria - dois
     * parametros) e salva o resultado no Registrador resultado.
     *
     * Os operandos podem estar em complemento de dois ou nao, depende da
     * operacao escolhida.
     * 
     * @param primeiroOperando Primeiro operando
     * @param codigoOperacao Codigo da operacao a ser executada
     * @return 'true' caso seja possivel realizar a operacao, 'false' caso nao
     *         seja.
     */
    public boolean operar(int codigoOperacao, int[] primeiroOperando) {
    
        if(primeiroOperando.length != this.tamanhoOperando)
            throw new IllegalArgumentException("O operando deve ter exatamente " + 
                    this.tamanhoOperando + " bits de tamanho");
        
        switch(codigoOperacao) {
            case 0: // incrementar
                
                break;
            
            case 1: // incrementar (comp 2)
                
                break;    
                
            case 2: // somar
                
                break;    
                
            case 3: // somar (comp 2)
                
                break;
                
            case 4: // subtrair (comp 2)
                
                break;
                
            case 5: // multiplicar (comp 2)
                
                break;
                
            case 6: // dividir (comp 2)
                
                break;
                
            default:
            
        }
        
        return false; 
    }
    
    
}
