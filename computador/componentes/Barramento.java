package computador.componentes;

import computador.conexao.Conectavel;
import computador.conexao.ConexaoBinaria;

/**
 * Classe que representa um barramento atraves de um variavel do tipo vetor
 * para inteiro.
 * 
 * Data: 31-05-2018
 */
public class Barramento implements Conectavel {
    
    // Variavel que representa o valor armazenado no barramento
    private int[] binario;
    
    
    
    /**
     * Cria um barramento com o tamanho especificado
     * 
     * @param tamanho Tamanho do barramento
     */
    public Barramento(int tamanho) {
        this.binario = new int[tamanho];
    }

    
    
    /**
     * Define o Barramento como destino de um objeto ConexaoBinaria.
     * 
     * @param conexao Conexao que tera o Barramento como destino
     * @return 'true' caso tenha sido possivel definir o Barramento como destino,
     *         ou false caso nao tenha sido.
     */
    @Override
    public boolean definirComoDestino(ConexaoBinaria conexao) {
        return conexao.definirDestino(this.binario);
    }
    
    /**
     * Define o Barramento como origem de um objeto ConexaoBinaria.
     * 
     * @param conexao Conexao que tera o Barramento como origem
     * @return 'true' caso tenha sido possivel definir o Barramento como origem,
     *         ou false caso nao tenha sido.
     */
    @Override
    public boolean definirComoOrigem(ConexaoBinaria conexao) {
        return conexao.definirOrigem(this.binario);
    }
    
    // APAGAR DPS
    public void imprimir() {
        for(int i = 0; i < this.binario.length; i++)
            System.out.print(this.binario[i]);
    }
    
}
