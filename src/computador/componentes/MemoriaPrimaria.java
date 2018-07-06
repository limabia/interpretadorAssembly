/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computador.componentes;

import computador.conexao.Conectavel;

/**
 *
 * @author marcelo
 */
public interface MemoriaPrimaria extends Operavel, Conectavel {
    
    public boolean enderecoValido();
    
    public void escreverBuffer(int[] binario);    
    public void escrever();
            
    public void ler();
    
}
