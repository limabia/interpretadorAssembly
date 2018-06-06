package computador.componentes;

/**
 *
 * Data 31-05-2018;
 */
public class CPU {
    
    UC UC;
    
    Barramento barramento;
    
    /* ########################### REGISTRADORES ########################### */
    
    // Proposito geral
    Registrador AX, BX, CX, DX;
    
    // Execucao da instrucao
    Registrador IR, PC;
    
    // Buffer para ULA
    Registrador T, AC;
    
    // Se comunicam com o barramento externo
    Registrador MAR, MBR;
    
    public CPU(int tamanhoBarramento, int tamanhoRegistrador, int tamanhoOP, 
            int tamanhoParametro1, int tamanhoParametro2, Barramento barramentoExterno,
            RAM RAM, ULA ULA) {
    
        this.UC = new UC(RAM, ULA);
        
        this.barramento = new Barramento(tamanhoBarramento);
        
        /* ######################### REGISTRADORES ######################### */
        
        // AX
        this.AX = new Registrador(tamanhoRegistrador);
        {
            // AX --(00)--> Barramento
            this.AX.definirComoOrigem(UC.obterConexao(1));            
            this.barramento.definirComoDestino(UC.obterConexao(1));

            // Barramento --(00)--> AX
            this.barramento.definirComoOrigem(UC.obterConexao(0));            
            this.AX.definirComoDestino(UC.obterConexao(0));
        }
        
        // BX
        this.BX = new Registrador(tamanhoRegistrador);
        {
            // BX --(00)--> Barramento
            this.BX.definirComoOrigem(UC.obterConexao(1));            
            this.barramento.definirComoDestino(UC.obterConexao(1));

            // Barramento --(00)--> BX
            this.barramento.definirComoOrigem(UC.obterConexao(0));            
            this.BX.definirComoDestino(UC.obterConexao(0));
        }
        
        // CX
        this.CX = new Registrador(tamanhoRegistrador);
        {
            // CX --(00)--> Barramento
            this.CX.definirComoOrigem(UC.obterConexao(1));
            this.barramento.definirComoDestino(UC.obterConexao(1));

            // Barramento --(00)--> CX
            this.barramento.definirComoOrigem(UC.obterConexao(0));            
            this.CX.definirComoDestino(UC.obterConexao(0));
        }
 
        // DX
        this.DX = new Registrador(tamanhoRegistrador);
        {
            // DX --(00)--> Barramento
            this.DX.definirComoOrigem(UC.obterConexao(1));            
            this.barramento.definirComoDestino(UC.obterConexao(1));

            // Barramento --(00)--> DX
            this.barramento.definirComoOrigem(UC.obterConexao(0));            
            this.DX.definirComoDestino(UC.obterConexao(0));
        }
        
        
        // IR
        this.IR = new Registrador(tamanhoOP, tamanhoParametro1, tamanhoParametro2);
        {
            // IR --(00)--> Barramento
            this.IR.definirComoOrigem(UC.obterConexao(1));            
            this.barramento.definirComoDestino(UC.obterConexao(1));

            // Barramento --(00)--> IR
            this.barramento.definirComoOrigem(UC.obterConexao(0));            
            this.IR.definirComoDestino(UC.obterConexao(0));
            
            // (IR)P1 --(00)--> Barramento
            this.IR.definirComoOrigem(UC.obterConexao(1), 1);            
            this.barramento.definirComoDestino(UC.obterConexao(1));

            // Barramento --(00)--> (IR)P1
            this.barramento.definirComoOrigem(UC.obterConexao(0));            
            this.IR.definirComoDestino(UC.obterConexao(0), 1);
            
            // (IR)P2 --(00)--> Barramento
            this.IR.definirComoOrigem(UC.obterConexao(1));            
            this.barramento.definirComoDestino(UC.obterConexao(1));

            // Barramento --(00)--> (IR)P2
            this.barramento.definirComoOrigem(UC.obterConexao(0));            
            this.IR.definirComoDestino(UC.obterConexao(0));
        }
        
        
        // PC
        this.PC = new Registrador(tamanhoRegistrador);
        {
            // PC --(00)--> Barramento
            this.PC.definirComoOrigem(UC.obterConexao(1));            
            this.barramento.definirComoDestino(UC.obterConexao(1));

            // Barramento --(00)--> PC
            this.barramento.definirComoOrigem(UC.obterConexao(0));            
            this.PC.definirComoDestino(UC.obterConexao(0));
        }
        
        
        // T
        this.T = new Registrador(tamanhoRegistrador);
        {
            // T --(00)--> Barramento
            this.T.definirComoOrigem(UC.obterConexao(1));            
            this.barramento.definirComoDestino(UC.obterConexao(1));

            // Barramento --(00)--> T
            this.barramento.definirComoOrigem(UC.obterConexao(0));            
            this.T.definirComoDestino(UC.obterConexao(0));
        }
        
        
        // AC
        this.AC = new Registrador(tamanhoRegistrador, tamanhoRegistrador);
        {
            // (AC)P1 --(00)--> Barramento
            this.AC.definirComoOrigem(UC.obterConexao(1), 0);            
            this.barramento.definirComoDestino(UC.obterConexao(1));

            // Barramento --(00)--> (AC)P1
            this.barramento.definirComoOrigem(UC.obterConexao(0));            
            this.AC.definirComoDestino(UC.obterConexao(0), 0);
            
             // (AC)P2 --(00)--> Barramento
            this.AC.definirComoOrigem(UC.obterConexao(1), 1);             
            this.barramento.definirComoDestino(UC.obterConexao(1));

            // Barramento --(00)--> (AC)P2
            this.barramento.definirComoOrigem(UC.obterConexao(0));            
            this.AC.definirComoDestino(UC.obterConexao(0), 1);
        }
        
        // MAR
        this.MAR = new Registrador(tamanhoRegistrador);
        {
            // MAR --(00)--> Barramento Externo
            this.MAR.definirComoOrigem(UC.obterConexao(1));            
            barramentoExterno.definirComoDestino(UC.obterConexao(1));
            
            // Barramento Externo --(00)--> MAR
            barramentoExterno.definirComoOrigem(UC.obterConexao(0));
            this.MAR.definirComoDestino(UC.obterConexao(0));
        }
        
        // MBR
        this.MBR = new Registrador(tamanhoRegistrador);
        {
            // MBR --(00)--> Barramento
            this.MBR.definirComoOrigem(UC.obterConexao(1));            
            this.barramento.definirComoDestino(UC.obterConexao(1));

            // Barramento --(00)--> MBR
            this.barramento.definirComoOrigem(UC.obterConexao(0));            
            this.MBR.definirComoDestino(UC.obterConexao(0));
            
            // MBR --(00)--> Barramento Externo
            this.MBR.definirComoOrigem(UC.obterConexao(1));            
            barramentoExterno.definirComoDestino(UC.obterConexao(1));
            
            // Barramento Externo --(00)--> MBR
            barramentoExterno.definirComoOrigem(UC.obterConexao(0));
            this.MBR.definirComoDestino(UC.obterConexao(0));
        }
    }
}
