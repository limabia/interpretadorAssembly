package computador;

import computador.componentes.Barramento;
import computador.componentes.CPU;
import computador.componentes.ComponentException;
import computador.componentes.MemoriaPrimaria;
import computador.componentes.RAM;
import computador.componentes.ULA;

/**
 *
 * Data: 31-05-2018
 */
public class Computador {
    
    public final int TAMANHO_PALAVRA;
    public final int TAMANHO_OPCODE;
    public final int TAMANHO_PARAMETRO1;
    public final int TAMANHO_PARAMETRO2;
    
    private CPU CPU;
    
    public MemoriaPrimaria memoriaPrimaria;
    
    private Barramento barramento;
    
    public Computador(int tamanhoPalavra) throws Exception {
        this.TAMANHO_PALAVRA = tamanhoPalavra;
        this.TAMANHO_OPCODE = 5;
        this.TAMANHO_PARAMETRO1 = (TAMANHO_PALAVRA - TAMANHO_OPCODE) / 2;
        this.TAMANHO_PARAMETRO2 = TAMANHO_PARAMETRO1 + (TAMANHO_PALAVRA - TAMANHO_OPCODE) % 2;
        
        this.barramento = new Barramento(this.TAMANHO_PALAVRA);

        this.memoriaPrimaria = new RAM(TAMANHO_PALAVRA, 0, 999);
        
        try {
            this.CPU = new CPU(this.TAMANHO_PALAVRA, this.TAMANHO_OPCODE, 
                this.TAMANHO_PARAMETRO1, this.TAMANHO_PARAMETRO2,
                this.barramento, this.memoriaPrimaria);
        } catch(ComponentException ce) {
            throw new Exception("Erro ao iniciar a CPU: " + ce.getMessage());
        }

    }
  
    public void executar() {
        this.CPU.clock();
    }
    
    public static void main(String[] args) {
  
        Computador computador = null;
        
        try {
            computador = new Computador(16);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao iniciar o computador: " + e.getMessage());
            System.exit(1);
        }
        
        int[] instrucaoMov = {0,0,0,0,0,  1,0,0,1,1,  0,0,0,0,0,1};
        computador.memoriaPrimaria.escreverBuffer(new int[16]);
        computador.memoriaPrimaria.enderecoValido();
        computador.memoriaPrimaria.escreverBuffer(instrucaoMov); // mudar o buffer e a RAM do computador pra private dps
        computador.memoriaPrimaria.escrever();
        
        computador.executar();
        computador.executar();
        computador.executar();
        computador.executar();
        computador.executar();
        computador.executar();
        computador.executar();
        computador.executar();
        
    }
}
