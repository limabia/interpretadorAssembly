package computador.componentes;

import computador.base_numerica.Binario;
import computador.conexao.ConexaoBinaria;
import java.util.HashMap;

/**
 * Esta classe simula uma RAM usando um HashMap que mapeia cada vetor de inteiros
 * (que representa um numero binario) atraves de um inteiro. Um objeto Registrador
 * eh usado para servidar buffer tanto para dados de leitura/escrita quanto para
 * enderecos.
 * 
 * Data: 31-05-2018
 */
public class RAM implements MemoriaPrimaria {
    
    // Representacao da mamoria
    private final HashMap<Integer, int[]> MEMORIA;
 
    // Registrador para armazenamento temporario de enderecoes e dados
    private final Registrador BUFFER;
    
    // Limites inclusivos para o endereco 
    private final int ENDERECO_MINIMO;
    private final int ENDERECO_MAXIMO;
    
    // Endereco no qual um valor deve ser lido ou escrito
    private int endereco;
    
    /**
     * Istancia um objeto RAM criando um buffer com o tamanho indicado e tendo
     * como limite do valor de um endereco os valores indicados. O valor padrao
     * do atributo 'endereco' eh definido como o valor minimo.
     * 
     * @param tamanhoBuffer Tamanho do buffer de enderecos/dados
     * @param enderecoMinimo Valor minimo que um endereco pode assumir
     * @param enderecoMaximo Valor maximo que um endereco pode assumir
     */
    public RAM(int tamanhoBuffer, int enderecoMinimo, int enderecoMaximo) {
        this.MEMORIA = new HashMap<>();
        
        this.BUFFER = new Registrador(tamanhoBuffer);
        
        this.ENDERECO_MINIMO = enderecoMinimo;
        this.ENDERECO_MAXIMO = enderecoMaximo;
        
        this.endereco = enderecoMinimo;
    }
    
    
    
    /**
     * Verifica se um codigo de uma operacao eh valido.
     * 
     * @param codigoOperacao Codigo da operacao
     * @return 'true' caso o codigo seja valido, 'false' caso nao seja.
     */
    public static boolean codigoValido(int codigoOperacao) {
        return (codigoOperacao >= 0 && codigoOperacao <= 3);
    }
    
    
    
    /**
     * Dado o codigo da operacao realiza a operacao envolvendo a RAM. -1 eh o
     * codigo para nao realizar nenhuma operacao.
     * 
     * @param codigoOperacao Codigo da operacao a ser realizada
     * @return 'true' caso seja possivel realizar a operacao (-1 inclusive) ou
     *         'false' caso nao seja
     */
    public boolean operar(int codigoOperacao) {
        switch(codigoOperacao) {
            case 0:
                // Nao faz nada
                return true;
                
            case 1: // Define o valor do buffer da RAM como um endereco
                this.enderecoValido();
                System.out.println("RAM: operar -> endereco valido");
                return true;

            case 2: // Escreve na RAM
                this.escrever();
                return true;

            case 3: // Faz a leitura na RAM
                this.ler();
                return true;
                
            default:
                return false;
        }
    }
    
    /**
     * Retorna o binario armazenado no BUFFER da RAM
     * 
     * @return Binario armazenado no BUFFER da RAM
     */
    @Override
    public String getValorBUFFER() {
        return this.BUFFER.toString();
    }
    
    /**
     * Retorna o valor do endereco armazenado na RAM
     * 
     * @return Valor do endereco armazenado na RAM
     */
    @Override
    public String getEndereco() {
        return Integer.toString(this.endereco);
    }
    
    /**
     * Escreve um programa inteiro na memoria a partir de um ponto inicial.
     * 
     * @param inicio Inicio de onde o programa sera salvo
     * @param programa Programa a ser salvo
     */
    @Override
    public void escreverPrograma(int inicio, int[][] programa) {
        
        this.endereco = inicio;
        
        for(int[] comando : programa) {
            this.BUFFER.escrever(comando);
            this.escrever();
            this.endereco++;
        }
    }
    
    /**
     * Verifica se o binario que esta salvo no Registrador buffer eh um endereco
     * valido. Caso seja, salva seu valor inteiro no atributo 'endereco' e retorna
     * true. Caso nao seja, retorna false.
     * 
     * @return 'true' caso o endereco tenha sido salvo com sucesso ou
     *         'false' caso nao tenha, isto eh, o endereco eh invalido.
     */
    @Override
    public boolean enderecoValido() {
        
        int valor = Binario.valorInteiroComplementoDeDois(BUFFER.ler());
        
        if(valor < this.ENDERECO_MINIMO || valor > this.ENDERECO_MAXIMO)
            return false; // Endereco invalido
        
        this.endereco = valor;
        //this.ler();
        return true;
    }
    
    /**
     * Escreve o conteudo do buffer na posicao de memoria indicada pelo atributo
     * 'endereco'.
     */
    @Override
    public void escrever() {
       this.MEMORIA.put(this.endereco, this.BUFFER.ler());
    }
    
    /**
     * Le o conteudo que esta armazenado no endereco indicado pelo atributo
     * 'endereco' e salva no BUFFER.
     */
    @Override
    public void ler() {
        int[] conteudo = this.MEMORIA.get(this.endereco);
        
        if(conteudo == null) conteudo = new int[BUFFER.obterTamanho()];
        
        this.BUFFER.escrever(conteudo);
    }
    
    @Override
    public int codigoOperacaoEnderecoValido() {
        return 1;
    }
    
    @Override
    public int codigoOperacaoEscrever() {
        return 2;
    }
    
    @Override
    public int codigoOperacaoLer() {
        return 3;
    }
    
    /**
     * Define o Registrador BUFFER como destino de uma conexao binaria.
     * 
     * @param conexao Conexao no qual o BUFFER sera adicionado
     * @return 'true' caso a operacao seja efetuada com sucesso ou 'false'
     *          caso nao seja.
     */
    @Override
    public boolean definirComoDestino(ConexaoBinaria conexao) {
        return this.BUFFER.definirComoDestino(conexao);
    }
    
    /**
     * Define o Registrador BUFFER como origem de uma conexao binaria.
     * 
     * @param conexao Conexao no qual o BUFFER sera adicionado
     * @return 'true' caso a operacao seja efetuada com sucesso ou 'false'
     *         caso nao seja.
     */
    @Override
    public boolean definirComoOrigem(ConexaoBinaria conexao) {
        return this.BUFFER.definirComoOrigem(conexao);
    }
    
}
