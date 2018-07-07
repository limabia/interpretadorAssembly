package computador;

import computador.base_numerica.Binario;
import computador.componentes.Barramento;
import computador.componentes.CPU;
import computador.componentes.ComponentException;
import computador.componentes.MemoriaPrimaria;
import computador.componentes.RAM;
import computador.componentes.StatusCPU;
import computador.componentes.ULA;
import java.io.File;

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
    
    private MemoriaPrimaria memoriaPrimaria;
    
    private Barramento barramento;
    
    public Computador(int tamanhoPalavra) throws Exception {
        this.TAMANHO_PALAVRA = tamanhoPalavra;
        this.TAMANHO_OPCODE = 5;
        this.TAMANHO_PARAMETRO1 = (TAMANHO_PALAVRA - TAMANHO_OPCODE) / 2;
        this.TAMANHO_PARAMETRO2 = TAMANHO_PARAMETRO1 + (TAMANHO_PALAVRA - TAMANHO_OPCODE) % 2;
        
        this.barramento = new Barramento(this.TAMANHO_PALAVRA);

        this.memoriaPrimaria = new RAM(this.TAMANHO_PALAVRA, 0, 999);
        
        try {
            this.CPU = new CPU(this.TAMANHO_PALAVRA, this.TAMANHO_OPCODE, 
                this.TAMANHO_PARAMETRO1, this.TAMANHO_PARAMETRO2,
                this.barramento, this.memoriaPrimaria);
        } catch(ComponentException ce) {
            throw new Exception("Erro ao iniciar a CPU: " + ce.getMessage());
        }

    }
    
    public StatusCPU getStatusCPU() {
        return this.CPU.getStatusCPU();
    }
  
    public void escreverPrograma(int[][] programa) {
        this.memoriaPrimaria.escreverPrograma(0, programa);
    }
    
    public void executar() {
        this.CPU.clock();
    }
    
    public static void IMPRIMIRTEMPORARIO(StatusCPU statusCPU) {
        
        System.out.println("AX:  " + statusCPU.getValorAX()  + "\tBX:  " + statusCPU.getValorBX());
        System.out.println("CX:  " + statusCPU.getValorCX()  + "\tDX:  " + statusCPU.getValorDX());
        System.out.println("MAR: " + statusCPU.getValorMAR() + "\tMBR: " + statusCPU.getValorMBR());
        System.out.println("IR:  " + statusCPU.getValorIR()  + "\tPC:  " + statusCPU.getValorPC());
        System.out.println("ENDERECO RAM:  " + statusCPU.getEnderecoMemoria()  + "\tBUFFER RAM:  " + statusCPU.getValorBUFFERMemoria());
        System.out.println("AC:  " + statusCPU.getValorAC()  + "\tT:  " + statusCPU.getValorT());
        
        System.out.print("Palavra Controle:");
        for(String s : statusCPU.getPalavraControle())
            System.out.print(" " + s);
        System.out.println("\n");
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
        
        int[][] programa = {
            //{0,0,0,0,1,  1,0,0,1,1,  0,0,0,0,1,0},  // MOV AX,2
            //{0,1,0,1,0,  1,0,0,1,1,  0,0,0,1,0,0},  // SUB AX,4
            //{0,0,0,0,1,  1,0,1,0,1,  0,0,0,0,1,0},  // MOV BX,2
            //{0,1,1,0,0,  1,0,1,0,1,  0,1,0,1,0,1},  // SUB AX,BX
            //{0,1,1,0,1,  1,0,0,1,1,  0,0,0,0,0,0},  // INC AX
            //{0,1,0,1,0,  1,0,0,1,1,  0,1,0,1,0,1}, // SUB AX,BX
            
            {0,1,1,0,1,  1,0,0,1,1,  0,0,0,0,0,0},  // INC AX
            {0,1,1,1,1,  1,0,0,1,1,  0,0,0,0,0,1},  // COMP AX,3
            {1,0,0,0,0,  0,0,0,0,0,  0,0,0,0,0,0},  // JLE 0
        };
        
        StatusCPU status = computador.getStatusCPU();
        
        computador.escreverPrograma(programa);
        
        do {
            try {
                computador.executar();
                IMPRIMIRTEMPORARIO(status);        
            } catch(Exception e) {
                System.out.println("\nDeu erro! Hora de parar!\n" + e.getMessage());
                break;
            }
        } while(true);
    }
}
