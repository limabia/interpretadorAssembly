package computador.componentes;

import computador.conexao.ConexaoBinaria;

/**
 *
 * Data: 31-05-2018
 */
public class UC {
    
    ConexaoBinaria[] conexoes;
    
    public UC(RAM RAM, ULA ULA) {
    
    }
    
    public ConexaoBinaria obterConexao(int posicao) {
        return this.conexoes[posicao];
    }
    
}
