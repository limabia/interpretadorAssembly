package computador.componentes;

import computador.conexao.Conectavel;
import computador.conexao.ConexaoBinaria;

/**
 * @since 31-05-2018
 */
public class Barramento implements Conectavel {
    
    private int[] binario;
    
    public Barramento(int tamanho) {
        this.binario = new int[tamanho];
    }

    
    
    @Override
    public boolean definirComoDestino(ConexaoBinaria conexao) {
        return conexao.definirDestino(this.binario);
    }
    
    @Override
    public boolean definirComoOrigem(ConexaoBinaria conexao) {
        return conexao.definirOrigem(this.binario);
    }
    
}
