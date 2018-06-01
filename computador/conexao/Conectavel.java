package computador.conexao;

/**
 * Classes cujas instancias sao detentoras de numeros binarios que serao usados
 * em um objeto Conexao Binaria devem implementar essa interface a fim de estabelecer
 * a conexao mantendo o encapsulamento do atributo compartilhado.
 * 
 * @since 31-05-2018
 */
public interface Conectavel {
    
    public boolean definirComoDestino(ConexaoBinaria conexao);
    public boolean definirComoOrigem(ConexaoBinaria conexao);
    
}
