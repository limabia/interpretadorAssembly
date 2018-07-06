package computador.componentes;

import computador.conexao.Conectavel;

/**
 *
 * @author marcelo
 */
public interface MemoriaPrimaria extends Operavel, Conectavel {
    
    public String getValorBUFFER();
    
    public String getEndereco();
    
    public boolean enderecoValido();
    
    public void escreverPrograma(int inicio, int[][] programa);
    
    public void escrever();
            
    public void ler();
    
    public int codigoOperacaoEnderecoValido();
    
    public int codigoOperacaoEscrever();
    
    public int codigoOperacaoLer();
}
