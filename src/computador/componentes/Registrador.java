package computador.componentes;

import computador.conexao.Conectavel;
import computador.conexao.ConexaoBinaria;

/**
 * Esta classe representa um registrador que armazena valores em binario. Eh usado
 * um vetor bidimensional de inteiros para representar o binario, pois cada
 * registrador pode ser dividido em particoes.
 * 
 * Data: 31-05-2018
 */
public class Registrador implements Conectavel {
    
    // Tamanho total do registrador (soma do tamanho de suas particoes)
    private final int TAMANHO;
    
    // Numero de particoes do registrador
    private final int NUMERO_PARTICOES;
    
    // Representacao do registrador com suas particoes
    private final int[][] binario;

    
    
    /**
     * Cria um registrador com uma unica particao cujo tamanho eh igual ao
     * valor especificado via parametro.
     * 
     * @param tamanho Tamanho do registrador
     */
    public Registrador(int tamanho) {
        this.binario = new int[1][tamanho];
        this.TAMANHO = tamanho;
        this.NUMERO_PARTICOES = 1;
    }
    
    /**
     * Cria multiplas particoes de acordo com os tamanhos indicados via parametro.
     * A particoes tem tamanho igual ao valor presente no mesmo indice.
     * 
     * @param tamanhos Tamanho de cada particao
     */
    public Registrador(int... tamanhos) {
        this.binario = new int[tamanhos.length][];
        
        int tamanho = 0;
        for(int i = 0; i < tamanhos.length; i++) {
            this.binario[i] = new int[tamanhos[i]];
            tamanho += tamanhos[i];
        }
        
        this.TAMANHO = tamanho;
        this.NUMERO_PARTICOES = tamanhos.length;
    }
    
    
    
    @Override
    public String toString() {
        
        String conteudo = "";
        
        for(int i = 0; i < this.binario.length; i++) {
            int j;
            for(j = 0; j < this.binario[i].length - 1; j++) {
                conteudo += (this.binario[i][j]);
            }
            if(j < this.binario[i].length)
                conteudo += this.binario[i][j];
        }
        
        return conteudo;
    }
    
    /**
     * Retorna o tamanho do registrador. Caso o registrador possua mais de uma
     * particao, isto eh, eh composto por mais de um vetor de inteiros, entao
     * o valor retornado sera igual a soma do tamanho de todos os vetores.
     * 
     * @return Tamanho do registrador
     */
    public int obterTamanho() {
        return this.TAMANHO;
    }
    
    /**
     * Retorna o numero de particoes do Registrador
     * 
     * @return numero de particoes do Registrador
     */
    public int numeroParticoes() {
        return this.NUMERO_PARTICOES;
    }
    
    /**
     * Escreve um valor binario dentro do Registrador de tal maneira que todas
     * as particoes sao consideradas como uma so. Dessa maneira, quando uma eh
     * esgota as escrita continua na ultima posicao da proxima particao.
     * Quando o binario sendo escrito possui tamanho inferior ao necessario os
     * demais bits do Registrador sao escritos iguais ao bit mais significativo
     * do numero sendo escrito, pois assim a propriedade de complemento de dois
     * eh mantida.
     * 
     * @param binario Numero binario em complemento de dois.
     * @return 'true' caso seja possivel escrever ou 'false' caso o tamanho do
     *         registrador seja menor do que o tamanho necessario
     */
    public boolean escrever(int[] binario) {
        
        if(binario.length > TAMANHO)
            return false;
        
        int t = binario.length - 1;
        
        int j, i = this.binario.length - 1;
        while(i >= 0 && t >= 0) {
            j = this.binario[i].length - 1;
            while(j >= 0 && t >= 0) {
                this.binario[i][j--] = binario[t--];
            }
            i--;
        }
        
        while(i >= 0) {
            j = this.binario[i].length - 1;
            while(j >= 0)
                this.binario[i][j--] = binario[0];
            i--;
        }
        
        return true;
    }
    
    /** 
     * Escreve um valor binario dentro de uma particao do Registrador. Caso o
     * o valor escrito tenha menos bits do que a particao a propriedade de 
     * complemento de dois eh mantida.
     * 
     * @param binario Binario a ser escrito na particao
     * @param particao Indice da particao
     * @return 'true' caso seja possivel escrever ou 'false' caso o tamanho da
     *          particao seja menor do que o tamanho necessario
     */
    public boolean escrever(int[] binario, int particao) {
        if(particao < 0 || particao >= this.binario.length)
            throw new IllegalArgumentException("Indice de particao invalido");
        
        if(binario.length > this.binario[particao].length)
            return false; // Binario com tamanho invalido
        
        int diferenca = this.binario[particao].length - binario.length;
        
        // Copia os bits do binario para a particao
        int i = this.binario[particao].length - 1;
        while(i >= diferenca)
            this.binario[particao][i + diferenca] = binario[i--];
        
        // Preenche os bits restantes
        while(i >= 0)
            this.binario[particao][i--] = binario[0];
        
        return true;
    }
    
    /**
     * Escreve um valor em um bit especifico do Registrador.
     * 
     * @param bit Valor a ser escrito
     * @param indice Indice do bit
     */
    public void escreverBit(int bit, int indice) {
        if(indice < 0 || indice >= this.TAMANHO)
            throw new IllegalArgumentException("Indice de particao invalido");
        
        int particao = 0;
        int posicao = 0;
        int contador = 0;
        
        while(contador < indice) {
            posicao++;
            if(posicao >= this.binario[particao].length) {
                particao++;
                posicao = 0;
            }
        }
        
        this.binario[particao][posicao] = bit;
    }
    
    /**
     * Cria um vetor do tamanho do Registrador e retorna. Caso haja mais de uma
     * particao no Registrador os bits sao copiados para o vetor de acordo com
     * o indice de seu registrador e seu proprio indice.
     * 
     * @return Numero binario contido em todo o Registrador
     */
    public int[] ler() {
        int[] temp = new int[this.TAMANHO];
        int t = this.TAMANHO - 1;
        
        int j, i = this.binario.length - 1;
        while(i >= 0) {
            j = this.binario[i].length - 1;
            while(j >= 0)
                temp[t--] = this.binario[i][j--];
            i--;
        }
        
        return temp;
    }
    
    /**
     * Cria um vetor do tamanho da particao indicada e retorna com o valor presente
     * na particao. 
     * 
     * @param particao Indice da particao
     * @return Vetor com o mesmo conteudo da particao
     */
    public int[] ler(int particao) {
        if(particao < 0 || particao >= this.binario.length)
            throw new IllegalArgumentException("Indice de particao invalido");
        
        int[] temp = new int[this.binario[particao].length];
        for(int i = 0; i < temp.length; i++)
            temp[i] = this.binario[particao][i];
        
        return temp;
    }
    
    /**
     * Retorna o valor de um bit especifico no Registrador.
     * 
     * @param indice Indice do bit
     * @return Valor do bit no indice indicado
     */
    public int lerBit(int indice) {
        if(indice < 0 || indice >= this.TAMANHO)
            throw new IllegalArgumentException("Indice de particao invalido");
        
        int particao = 0;
        int posicao = 0;
        int contador = 0;
        
        while(contador < indice) {
            posicao++;
            if(posicao >= this.binario[particao].length) {
                particao++;
                posicao = 0;
            }
        }
        
        return this.binario[particao][posicao];
    }
    
    /**
     * Define uma particao especificada como destino de um objeto ConexaoBinaria.
     * 
     * @param conexao Conexao a ter a particao como destino
     * @param particao Indice da particao
     * @return 'true' caso tenha sido possivel definir a particao como destino,
     *         ou false caso nao tenha sido.
     */
    public boolean definirComoDestino(ConexaoBinaria conexao, int particao) {
        if(particao < 0 || particao >= this.binario.length)
            throw new IllegalArgumentException("Indice de particao invalido");
        
        return conexao.definirDestino(this.binario[particao]);
    }
    
    /**
     * Define uma particao especificada como origem de um objeto ConexaoBinaria.
     * 
     * @param conexao Conexao a ter a particao como origem
     * @param particao Indice da particao
     * @return 'true' caso tenha sido possivel definir a particao como origem,
     *         ou false caso nao tenha sido.
     */
    public boolean definirComoOrigem(ConexaoBinaria conexao, int particao) {
        if(particao < 0 || particao >= this.binario.length)
            throw new IllegalArgumentException("Indice de particao invalido");
        
        return conexao.definirOrigem(this.binario[particao]);
    }

    /**
     * Define o Registrador inteiro como destino de um objeto ConexaoBinaria.
     * 
     * @param conexao Conexao a ter o Registrador como destino
     * @return 'true' caso tenha sido possivel definir o Registrador como destino,
     *         ou false caso nao tenha sido.
     */
    @Override
    public boolean definirComoDestino(ConexaoBinaria conexao) {
        return conexao.definirDestino(this.binario);
    }
    
    /**
     * Define o Registrador inteiro como origem de um objeto ConexaoBinaria.
     * 
     * @param conexao Conexao a ter o Registrador como origem
     * @return 'true' caso tenha sido possivel definir o Registrador como origem,
     *         ou false caso nao tenha sido.
     */
    @Override
    public boolean definirComoOrigem(ConexaoBinaria conexao) {
        return conexao.definirOrigem(this.binario);
    }

}
