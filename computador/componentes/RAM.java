package computador.componentes;

import computador.componentes.Registrador;
import computador.conexao.Conectavel;
import computador.conexao.ConexaoBinaria;
import java.util.HashMap;

/**
 *
 * @since 31-05-2018
 */
public class RAM implements Conectavel {
    
    private HashMap<Integer, int[]> memoria;
 
    private Registrador buffer;
    
    private final int enderecoMinimo;
    private final int enderecoMaximo;
    
    private int endereco;
    
    public RAM(int tamanhoBuffer, int enderecoMinimo, int enderecoMaximo) {
        this.memoria = new HashMap<>();
        
        this.buffer = new Registrador(tamanhoBuffer);
        
        this.enderecoMinimo = enderecoMinimo;
        this.enderecoMaximo = enderecoMaximo;
        
        this.endereco = enderecoMinimo;
    }
    
    public boolean enderecoValido() {
        
        /*
          Le o que ta no buffer e converte pra inteiro.
          depois verifica se eh valido. Se nao for retorn false, se for
          salva em endereco.
        */
        
        return true;
    }
    
    public void escrever() {
       this.memoria.put(this.endereco, this.buffer.ler());
    }
    
    public void ler() {
        this.buffer.escrever(this.memoria.get(this.endereco));
    }
    
    @Override
    public boolean definirComoDestino(ConexaoBinaria conexao) {
        return this.buffer.definirComoDestino(conexao);
    }
    
    @Override
    public boolean definirComoOrigem(ConexaoBinaria conexao) {
        return this.buffer.definirComoOrigem(conexao);
    }
    
}
