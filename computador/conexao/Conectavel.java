package computador.conexao;

/**
 * Classes cujas instancias sao detentoras de numeros binarios que serao usados
 * em um objeto Conexao Binaria devem implementar essa interface a fim de estabelecer
 * a conexao mantendo o encapsulamento do atributo compartilhado.
 * 
 * Data: 31-05-2018
 */
public interface Conectavel {
    
    /**
     * Define algum vetor inteiro como destino da conexao indicada por parametro.
     * 
     * @param conexao Conexao que tera o vetor como destino
     * @return 'true' caso tenha sido possivel definir o vetor como destino,
     *         ou false caso nao tenha sido.
     */
    public boolean definirComoDestino(ConexaoBinaria conexao);
    
    /**
     * Define algum vetor inteiro como origem da conexao indicada por parametro.
     * 
     * @param conexao Conexao que tera o vetor como origem
     * @return 'true' caso tenha sido possivel definir o vetor como origem,
     *         ou false caso nao tenha sido.
     */
    public boolean definirComoOrigem(ConexaoBinaria conexao);
    
}
