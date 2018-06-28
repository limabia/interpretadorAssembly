package computador.componentes;

import computador.conexao.Conectavel;
import computador.conexao.ConexaoBinaria;

/**
 *
 * Data: 31-05-2018
 */
public class ULA implements Conectavel {
    
    private final Registrador operando1;
    
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
        
        this.operando1 = new Registrador(tamanhoOperando);
        
        this.segundoOperando = segundoOperando;
        this.resultado = resultado;
        
        this.flagZero = false;
        this.flagSinalPositivo = false;
        this.flagOverflow = false;
        
    }
    
    
    
    /**
     * Verifica se um codigo de uma operacao eh valido.
     * 
     * @param codigoOperacao Codigo da operacao
     * @return 'true' caso o codigo seja valido, 'false' caso nao seja.
     */
    public static boolean codigoValido(int codigoOperacao) {
        return (codigoOperacao >= 0 && codigoOperacao <= 7);
    }
    
    
    
    /**
     * Retorna o valor da flag de resultado zero.
     * 
     * @return 'true' se o ultimo resultado for zero, 'false' caso contrario.
     */
    public boolean flagZero() {
        return this.flagZero;
    }
    
    /**
     * Retorna o valor da flag de sinal positivo.
     * 
     * @return 'true' caso o ultimo resultado tenha sido positivo, 'false' caso
     *         contrario.
     */
    public boolean sinalPositivo() {
        return this.flagSinalPositivo;
    }
    
    /**
     * Retorna o valor da flag de overflow.
     * 
     * @return 'true' caso a ultima operacao tenha dado overflow, 'false' caso
     *         contrario.
     */
    public boolean flagOverflow() {
        return this.flagOverflow;
    }
    
    
    
    /**
     * Dado um codigo de operacao e um operando realiza uma operacao com o
     * primeiroOPerando e com o valor armazenado no Registrador segundoParametro
     * (caso seja uma operacao binaria - dois parametros) e salva o resultado no
     * Registrador resultado.
     *
     * Os operandos podem estar em complemento de dois ou nao, depende da
     * operacao escolhida.
     * 
     * @param codigoOperacao Codigo da operacao a ser executada
     * @return 'true' caso seja possivel realizar a operacao, 'false' caso nao
     *         seja.
     */
    public boolean operar(int codigoOperacao) {
        
        switch(codigoOperacao) {
            case 0:
                // Nao faz nada
                return true;
            
            case 1: // incrementar
                
                return true;
            
            case 2: // incrementar (comp 2)
                
                return true;    
                
            case 3: // somar
                
                return true;    
                
            case 4: // somar (comp 2)
                
                return true;
                
            case 5: // subtrair (comp 2)
                
                return true;
                
            case 6: // multiplicar (comp 2)
                
                return true;
                
            case 7: // dividir (comp 2)
                
                return true;
                
            default:
            
        }
        
        return false; 
    }
    
    /**
     * Define o Registrador interno como destino de um objeto ConexaoBinaria.
     * 
     * @param conexao Conexao a ter o Registrador interno como destino
     * @return 'true' caso tenha sido possivel definir o Registrador interno como
     *         destino, ou false caso nao tenha sido.
     */
    @Override
    public boolean definirComoDestino(ConexaoBinaria conexao) {
        return this.operando1.definirComoDestino(conexao);
    }
    
    /**
     * Define o Registrador interno como origem de um objeto ConexaoBinaria.
     * 
     * @param conexao Conexao a ter o Registrador interno como origem
     * @return 'true' caso tenha sido possivel definir o Registrador interno como
     *         origem, ou false caso nao tenha sido.
     */
    @Override
    public boolean definirComoOrigem(ConexaoBinaria conexao) {
        return this.operando1.definirComoOrigem(conexao);
    }
    
}
