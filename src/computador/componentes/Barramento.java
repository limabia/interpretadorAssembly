package computador.componentes;

import computador.conexao.Conectavel;
import computador.conexao.ConexaoBinaria;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que representa um barramento atraves de uma variavel do tipo vetor
 * para inteiro.
 * 
 * Data: 31-05-2018
 */
public class Barramento implements Conectavel {
    
    // Variavel que representa o valor armazenado no barramento
    private int[] binario;
    
    // Conjuntos de conexoes envolvendo o Barramento
    private final ArrayList<ConexaoBinaria> entradas; // Conexoes no qual o Barramento eh o destino
    private final ArrayList<ConexaoBinaria> saidas; // Conexoes no qual o Barramento eh a origem
    
    
    
    /**
     * Cria um barramento com o tamanho especificado
     * 
     * @param tamanho Tamanho do barramento
     */
    public Barramento(int tamanho) {
        this.binario = new int[tamanho];

        this.entradas = new ArrayList();        
        this.saidas = new ArrayList();
    }
    
    
    
    /**
     * Ativa o enviao de dados comecando pelas conexoes abertas que escrevem no
     * barramento e terminando pelas conexoes abertas que leem do barramento.
     * Apos o metodo 'conectar' de cada conexao ser chamado, o metodo 'fechar'
     * da mesma conexao eh chamdo para fechar a conexao.
     */
    public void conectar() {
        Iterator<ConexaoBinaria> conexoes;
        ConexaoBinaria conexao;
        
        // Ativa as conexoes que escrevem no barramento
        conexoes = this.entradas.iterator();
        while(conexoes.hasNext()) {
            conexao = conexoes.next();
            conexao.conectar();
            conexao.fechar();
        }
        // Ativa as conexoes que leem do barramento
        conexoes = this.saidas.iterator();
        while(conexoes.hasNext()) {
            conexao = conexoes.next();
            conexao.conectar();
            conexao.fechar();
        }
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
        this.entradas.add(conexao);
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
        this.saidas.add(conexao);
        return conexao.definirOrigem(this.binario);
    }
    
    // APAGAR DPS
    public void imprimir() {
        for(int i = 0; i < this.binario.length; i++)
            System.out.print(this.binario[i]);
    }
    
}
