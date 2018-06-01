package computador.componentes;

import computador.conexao.Conectavel;
import computador.conexao.ConexaoBinaria;

/**
 * Esta classe representa um registrador que armazena valores em binario. Eh usado
 * um vetor bidimensional de inteiros para representar o binario, pois cada
 * registrador pode ser dividido em particoes.
 * 
 * @since 31-05-2018
 */
public class Registrador implements Conectavel {
    
    private int tamanho;
    
    private int[][] binario;
    
    /*
       Cria um registrador com uma unica particao cujo tamanho eh igual ao
       valor especificado via parametro.
    */
    public Registrador(int tamanho) {
        this.binario = new int[1][tamanho];
        this.tamanho = tamanho;
    }
    
    /*
      Cria multiplas particoes de acordo com os tamanhos indicados via parametro.
      A particoes tem tamanho igual ao valor presente no mesmo indice.
    */
    public Registrador(int... tamanhos) {
        this.binario = new int[tamanhos.length][];
        
        this.tamanho = 0;
        for(int i = 0; i < tamanhos.length; i++) {
            this.binario[i] = new int[tamanhos[i]];
            this.tamanho += tamanhos[i];
        }
    }
    
    public boolean escrever(int[] binario) {
        if(binario.length > tamanho) return false;
        
        int t = this.tamanho - 1;
        
        int j, i = this.binario.length - 1;
        while(i >= 0) {
            j = this.binario[i].length - 1;
            while(j >= 0)
                this.binario[i][j--] = binario[t--];
        }
        
        return true;
    }
    
    public int[] ler() {
        int[] temp = new int[this.tamanho];
        int t = this.tamanho - 1;
        
        int j, i = this.binario.length - 1;
        while(i >= 0) {
            j = this.binario[i].length - 1;
            while(j >= 0)
                temp[t--] = this.binario[i][j--];
        }
        
        return temp;
    }
    
    
    /*
      Define uma particao especificada como destino de um objeto ConexaoBinaria.
    */
    public boolean definirComoDestino(ConexaoBinaria conexao, int particao) {
        if(particao < 0 || particao >= this.binario.length)
            throw new IllegalArgumentException("Indice de particao invalido");
        
        return conexao.definirDestino(this.binario[particao]);
    }
    
    /*
      Define uma particao especificada como origem de um objeto ConexaoBinaria.
    */
    public boolean definirComoOrigem(ConexaoBinaria conexao, int particao) {
        if(particao < 0 || particao >= this.binario.length)
            throw new IllegalArgumentException("Indice de particao invalido");
        
        return conexao.definirOrigem(this.binario[particao]);
    }
    
    /*
      Define o Registrador inteiro como destino de um objeto ConexaoBinaria.
    */
    @Override
    public boolean definirComoDestino(ConexaoBinaria conexao) {
        return conexao.definirDestino(this.binario);
    }
    
    /*
      Define o Registrador inteiro como origem de um objeto ConexaoBinaria.
    */
    @Override
    public boolean definirComoOrigem(ConexaoBinaria conexao) {
        return conexao.definirOrigem(this.binario);
    }
}
