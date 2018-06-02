package computador;

import computador.componentes.Barramento;
import computador.componentes.RAM;
import computador.componentes.Registrador;
import computador.conexao.ConexaoBinaria;

/**
 *
 * @author marcelo
 */
public class Teste {

    public static void imprimir(int[] binario) {
        for(int v : binario)
            System.out.print(v);
    }
    
    public static void main(String[] args) {
    
        Barramento barramento = new Barramento(8);
        
        Registrador A = new Registrador(8);
        ConexaoBinaria a_bar = new ConexaoBinaria();
        ConexaoBinaria bar_a = new ConexaoBinaria();
        {            
            // A ----> Barramento
            A.definirComoOrigem(a_bar);            
            barramento.definirComoDestino(a_bar);

            // Barramento ----> A
            barramento.definirComoOrigem(bar_a);            
            A.definirComoDestino(bar_a);
        }
    
        Registrador B = new Registrador(8);
        ConexaoBinaria b_bar = new ConexaoBinaria();
        ConexaoBinaria bar_b = new ConexaoBinaria();        
        {   
            // B ----> Barramento
            B.definirComoOrigem(b_bar);            
            barramento.definirComoDestino(b_bar);

            // Barramento ----> B
            barramento.definirComoOrigem(bar_b);            
            B.definirComoDestino(bar_b);
        }
        
        Registrador C = new Registrador(4, 4);
        ConexaoBinaria c_bar = new ConexaoBinaria();
        ConexaoBinaria bar_c = new ConexaoBinaria();
        
        ConexaoBinaria c1_bar = new ConexaoBinaria();
        ConexaoBinaria bar_c1 = new ConexaoBinaria();
        
        ConexaoBinaria c2_bar = new ConexaoBinaria();
        ConexaoBinaria bar_c2 = new ConexaoBinaria();
        {
            // C ----> Barramento
            C.definirComoOrigem(c_bar);            
            barramento.definirComoDestino(c_bar);

            // Barramento ----> C
            barramento.definirComoOrigem(bar_c);            
            C.definirComoDestino(bar_c);
            
            // C1 ----> Barramento
            C.definirComoOrigem(c1_bar, 0);            
            barramento.definirComoDestino(c1_bar);

            // Barramento ----> C1
            barramento.definirComoOrigem(bar_c1);            
            C.definirComoDestino(bar_c1, 0);
            
            // C2 ----> Barramento
            C.definirComoOrigem(c2_bar, 1);            
            barramento.definirComoDestino(c2_bar);

            // Barramento ----> C2
            barramento.definirComoOrigem(bar_c2);            
            C.definirComoDestino(bar_c2, 1);
        }
        
        Barramento barramentoExterno = new Barramento(8);
        
        RAM RAM = new RAM(8, 0, 255);
        ConexaoBinaria ram_barEx = new ConexaoBinaria();
        ConexaoBinaria barEx_ram = new ConexaoBinaria();
        {
            // RAM(buffer) ----> Barramento Externo
            RAM.definirComoOrigem(ram_barEx);
            barramentoExterno.definirComoDestino(ram_barEx);
            
            // Barramento Externo ----> RAM(buffer)
            barramentoExterno.definirComoOrigem(barEx_ram);
            RAM.definirComoDestino(barEx_ram);
        }
        
        Registrador MAR  = new Registrador(8);
        ConexaoBinaria bar_mar = new ConexaoBinaria();
        ConexaoBinaria mar_barEx = new ConexaoBinaria();
        {
            // Barramento ----> MAR
            barramento.definirComoOrigem(bar_mar);
            MAR.definirComoDestino(bar_mar);
            
            // MAR ----> Barramento Externo
            MAR.definirComoOrigem(mar_barEx);
            barramentoExterno.definirComoDestino(mar_barEx);
        }
        
        Registrador MBR  = new Registrador(8);
        ConexaoBinaria mbr_bar = new ConexaoBinaria();
        ConexaoBinaria bar_mbr = new ConexaoBinaria();
        ConexaoBinaria mbr_barEx = new ConexaoBinaria();
        ConexaoBinaria barEx_mbr = new ConexaoBinaria();
        {
            // MBR ----> Barramento
            MBR.definirComoDestino(mbr_bar);
            barramento.definirComoOrigem(mbr_bar);
            
            // Barramento ----> MBR
            barramento.definirComoOrigem(bar_mbr);
            MBR.definirComoDestino(bar_mbr);
            
            // MBR ----> Barramento Externo
            MBR.definirComoDestino(mbr_barEx);
            barramentoExterno.definirComoOrigem(mbr_barEx);
            
            // Barramento Externo ----> MBR
            barramentoExterno.definirComoOrigem(barEx_mbr);
            MBR.definirComoDestino(barEx_mbr);
        }
        
        System.out.println("#1"); // OK
        /*
          Teste de conectividade Registrador -> Barramento -> Registrador
        */
        {
            int[] aux = {1,0,1,0,1,0,1,0};
            A.escrever(aux);
            
            a_bar.abrir();
            bar_b.abrir();
            
            System.out.print("Conteudo do A: ");
            imprimir(A.ler());
            System.out.print("\nConteudo do B: ");
            imprimir(B.ler());
            System.out.println("\n");
        }
        
        System.out.println("#2"); // OK
        /*
          Teste de conectividade Registrador(1) -> Barramento -> Registrador
        */
        {
            int[] aux = {1,1,1,1,0,1,1,1};
            C.escrever(aux);
            
            c1_bar.abrir();
            bar_a.abrir();
            
            System.out.print("Conteudo do C: ");
            imprimir(C.ler());
            System.out.print("\nConteudo do C1: ");
            imprimir(C.ler(0));
            System.out.print("\nConteudo do A: ");
            imprimir(A.ler());
            System.out.println("\n");
        }
        
        System.out.println("#3");
        /*
          Envia o conteudo do registrador B para a memoria e da memoria para o registrador A
        */
        {
            // T1
            {
                c2_bar.abrir();    // barramento <-- C(c2)
                bar_mar.abrir();   // MAR <-- Barramento
            }
            
            // T2
            {
                b_bar.abrir();     // barramento <-- B
                bar_mbr.abrir();    // mbr <-- barramento
                mar_barEx.abrir(); // barramento externo <-- MAR
                barEx_ram.abrir(); // RAM(buffer) <-- barramento externo          
                
                // Esta operacao deve ser realizada so dps que o buffer tiver o endereco
                RAM.enderecoValido();
            }
            
            // T3
            {
                mbr_barEx.abrir(); // Barramento externo <-- MBR
                barEx_ram.abrir(); // RAM(buffer) <-- Barramento externo
            
                RAM.escrever();
            }
            
            // T4
            {
                // O campo endereco da RAM nao foi modificado, logo n eh necessario
                // mandar o endereco again
                //mar_barEx.abrir(); // barramento externo <-- MAR
                //barEx_ram.abrir(); // RAM(buffer) <-- barramento externo 
                //RAM.enderecoValido();
                
                ram_barEx.abrir(); // Barramento Externo <-- RAM(buffer)
                barEx_mbr.abrir(); // MBR <-- Barramento Externo
            }
            
            // T5
            {
                mbr_bar.abrir(); // Barramento <-- MBR
                bar_a.abrir();   // A <-- Barramento
            }

            System.out.print("Conteudo do B: ");
            imprimir(B.ler());
            System.out.print("\nConteudo do A: ");
            imprimir(A.ler());
            System.out.println("\n");
        }
        
    }
    
}
