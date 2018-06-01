package computador.conexao;

/**
 * Cada instancia desta classe representa uma conexao entre dois vetores bidimensionais
 * de inteiros que representam um numero binario.
 * 
 * @since 31-05-2018
 */
public class ConexaoBinaria {
    
    private int[][] origem;
    private int[][] destino;
    
    private int tamanhoOrigem;
    private int tamanhoDestino;
    
    private int diferenca;
    
    /*
      Setter para a variavel origem. Retorna true caso seja possivel definir uma
      origem, ou false caso nao seja.
    */
    public boolean definirOrigem(int[] origem) {
        if(origem == null) return false;
        
        if(this.destino != null) {
            if(origem.length > tamanhoDestino)
                return false;
        
            this.diferenca = this.tamanhoDestino - origem.length;
        }
        
        this.origem = new int[1][];
        this.origem[0] = origem;
        this.tamanhoOrigem = origem.length;
        
        return true;
    }
    
    /*
      Setter para a variavel origem. Retorna true caso seja possivel definir uma
      origem, ou false caso nao seja.
    */
    public boolean definirOrigem(int[][] origem) {
        if(origem == null) return false;
        
        int tamanhoOrigem = 0;
        for(int[] o : origem)
            tamanhoOrigem += o.length;
        
        if(this.destino != null) {
            if(tamanhoOrigem > this.tamanhoDestino)
                return false;
            
            this.diferenca = this.tamanhoDestino - tamanhoOrigem;
        }
        
        this.origem = new int[origem.length][];
        for(int i = 0; i < origem.length; i++)
            this.origem[i] = origem[i];
        this.tamanhoOrigem = tamanhoOrigem;
        
        return true;
    }
    
    /*
      Setter para a variavel destino. Retorna true caso seja possivel definir um
      destino, ou false caso nao seja.
    */
    public boolean definirDestino(int[] destino) {
        if(destino == null) return false;
        
        if(this.origem != null) {
            if(destino.length < this.tamanhoOrigem)
                return false;
        
            this.diferenca = destino.length - this.tamanhoOrigem;
        }
        
        this.destino = new int[1][];
        this.destino[0]= destino;
        this.tamanhoDestino = destino.length;
        
        return true;
    }
   
    /*
      Setter para a variavel destino. Retorna true caso seja possivel definir um
      destino, ou false caso nao seja.
    */
    public boolean definirDestino(int[][] destino) {
        if(destino == null) return false;
        
        int tamanhoDestino = 0;
        for(int[] d : destino)
            tamanhoDestino += d.length;
        
        if(this.origem != null) {
            if(tamanhoDestino < this.tamanhoOrigem)
                return false;
            
            this.diferenca = tamanhoDestino - this.tamanhoOrigem;
        } 
        
        this.destino = new int[destino.length][];
        for(int i = 0; i < destino.length; i++)
            this.destino[i] = destino[i];
        this.tamanhoDestino = destino.length;
        
        return true;
    }
    
    /*
      Quando a conexao eh aberta o conteudo de 'origem' eh copiado para 'destino'.
      Caso o destino tenha um tamanho maior do que a origem, os espacos restantes
      sao preenchidos com o ultimo bit da 'origem', pois assim a propriedade de
      complemento de dois eh mantida.
      Quando o destino ou a origem (ou ambos) eh formada por vetores, entao a copia
      do conteudo eh feita como se todos os vetores fossem um so.
    */
    public boolean abrir() {
        if(this.origem == null || this.destino == null) return false;
        
        int i = this.destino.length - 1;
        int j = this.destino[i].length - 1;
        
        int l = this.origem.length - 1;
        int m = this.origem[l].length - 1;
        
        while(i >= 0 && l >= 0) {
            
            // Copia bit a bit
            while(j >= 0 && m >= 0)
                this.destino[i][j--] = this.origem[j][m--];
            
            // Passa para o proximo vetor do destino, caso necessario
            if(j < 0) {
                i--;
                if(i >= 0) j = this.destino[i].length - 1;
            }
            
            // Passa para o proximo vetor da origem, caso necessario
            if(m < 0) {
                l--;
                if(l >= 0) m = this.origem[l].length - 1;
            }
            
        }
        
        /* 
          O destino eh sempre maior ou igual a origem, assim os bits da origem
          sempre acabarao ao mesmo tempo ou antes do que os espacos do destino.
          Dessa forma, caso haja espaco no destino com a origem esgotava, o ultimo
          bit da origem (indice 0) eh copiado para os restantes do destino. Isso
          mantem a propriedade de complmento de dois.
        */
        while(i >= 0) {
            
            while(j >= 0)
                this.destino[i][j] = this.origem[0][0];
            
            i--;
            if(i >= 0) j = this.destino[i].length - 1;
        }
        
        
        /* Versao antiguinha
        int i;
        
        for(i = this.destino.length - 1; i >= this.diferenca; i--)
            this.destino[i] = this.origem[i - this.diferenca];
        
        // Mantem a propriedade de complemento de dois, caso os binarios tenham
        // tamanhos diferentes
        for(; i >= 0; i--)
            this.destino[i] = this.origem[0];
        */
        
        return true;
    }
    
    /*
      Uma conexao eh valida se tanto o 'destino' quanto a 'origem' forem nao nulos.
    */
    public boolean valida() {
        return (this.origem != null && this.destino != null);
    }

}
