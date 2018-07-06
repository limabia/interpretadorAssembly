package computador.componentes;

import computador.Teste;
import computador.conexao.ConexaoBinaria;

/**
 *
 * Data 31-05-2018;
 */
public class CPU {
    
    String MICROPORGRAMA_GAMTEMPORARIA = "#########################################################################\n" +
"# Estrutura da palavra de horizontal\n" +
"#\n" +
"# [0][1][2] [3] [4] [5] [6][7][8] [9]\n" +
"#\n" +
"# Legenda:\n" +
"#\n" +
"# 0: flag para o uso da entrada P1 (1 = usa, 0 = nao usa)\n" +
"# 1: flag para o uso da saida P1\n" +
"# 2: flag para o uso da saida P2\n" +
"#\n" +
"# 3: sinais de controle\n" +
"#\n" +
"# 4: $ sinais de operacao para ULA\n" +
"#    0: incrementar\n" +
"#    1: incrementar (comp 2)\n" +
"#    2: somar\n" +
"#    3: somar (comp 2)\n" +
"#    4: subtrair (comp 2)\n" +
"#    5: multiplicar (comp 2)\n" +
"#    6: dividir (comp 2)\n" +
"#\n" +
"# 5: @ sinais de operacao para RAM\n" +
"#    0: endereco valido\n" +
"#    1: escrever\n" +
"#    2: ler\n" +
"#\n" +
"# Condicoes do JUMP\n" +
"# 6: I jump incondicional\n" +
"# 7: Z jump zero\n" +
"# 8: N jump negativo\n" +
"# 9: P jump positivo\n" +
"#\n" +
"#10: & flag para a leitura do endereco da operacao no IR\n" +
"#\n" +
"#11: % endereco da microinstrucao em caso de jump\n" +
"#\n" +
"#\n" +
"# PADRAO:\n" +
"# FLAGS				SINAIS DE CONTROLE\n" +
"# 0 0 0   0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0   $   @   I Z N P   &   %\n" +
"#\n" +
"#########################################################################\n" +
"\n" +
"################################################################################################\n" +
"####################################### CICLO DE BUSCA #########################################\n" +
"################################################################################################\n" +
"#########0 0 0 0 0 0 0 0 0##1 1 1 1 1 1 1 1 1 1##2 2 2 2 2 2 2 2 2 2##3#########################\n" +
"#########1 2 3 4 5 6 7 8 9##0 1 2 3 4 5 6 7 8 9##0 1 2 3 4 5 6 7 8 9##0#########################\n" +
" 0 0 0   0 0 0 0 0 1 0 0 0  0 0 0 0 0 0 0 1 0 0  0 0 0 0 0 0 0 0 0 0  0   0   0   0 0 0 0  0   0\n" +
" 0 0 0   1 0 1 0 0 0 0 0 1  0 0 0 0 0 0 0 1 0 0  0 0 0 0 0 0 0 0 0 0  0   2   1   0 0 0 0  0   0\n" +
" 0 0 0   0 1 0 0 1 0 0 0 0  0 1 0 0 0 0 0 0 1 0  0 0 0 0 0 0 0 0 0 0  0   0   0   0 0 0 0  0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 1 0  0 0 0 0 1 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0   0   0   0 0 0 0  0   0\n" +
"\n" +
"################################################################################################\n" +
"##################################### CICLO DE EXECUCAO ########################################\n" +
"################################################################################################\n" +
"#########0 0 0 0 0 0 0 0 0##1 1 1 1 1 1 1 1 1 1##2 2 2 2 2 2 2 2 2 2##3#########################\n" +
"#########1 2 3 4 5 6 7 8 9##0 1 2 3 4 5 6 7 8 9##0 1 2 3 4 5 6 7 8 9##0#########################\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0   0   0   1 0 0 0  1   0\n" +
"\n" +
"#       0 0 0 0 0 0 0 0 0  1 1 1 1 1 1 1 1 1 1  2 2 2 2 2 2 2 2 2 2  3\n" +
"#       1 2 3 4 5 6 7 8 9  0 1 2 3 4 5 6 7 8 9  0 1 2 3 4 5 6 7 8 9  0\n" +
"\n" +
"$ OPCODE  0  (MOV REG,CONST)\n" +
" 1 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 1 0 0 0  0 0 0 0 0 0 0 0 0 0  0   0   0   1 0 0 0  0   0";
    
    UC UC;
    
    ULA ULA;
    
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
    
    public CPU(int tamanhoPalavra, int tamanhoOP, int tamanhoParametro1,
            int tamanhoParametro2, Barramento barramentoExterno, 
            MemoriaPrimaria memoriaPrimaria) throws ComponentException {
    
        this.barramento = new Barramento(tamanhoPalavra);
        Barramento[] barramentos = {this.barramento, barramentoExterno};
        
        this.AX = new Registrador(tamanhoPalavra);
        this.BX = new Registrador(tamanhoPalavra);
        this.CX = new Registrador(tamanhoPalavra);
        this.DX = new Registrador(tamanhoPalavra);
        
        this.IR = new Registrador(tamanhoOP, tamanhoParametro1, tamanhoParametro2);
        
        this.T = new Registrador(tamanhoPalavra);
        this.AC = new Registrador(tamanhoPalavra, tamanhoPalavra);
        
        this.ULA = new ULA(tamanhoPalavra, T, AC);
        
        this.UC = new UC(memoriaPrimaria, this.ULA, barramentos, this.IR, 26, MICROPORGRAMA_GAMTEMPORARIA);
        
        /* ######################### REGISTRADORES ######################### */
        
        ConexaoBinaria conexao;
        
        // AX
        {
            conexao = new ConexaoBinaria(true);
            
            // AX --(00)--> Barramento
            this.AX.definirComoOrigem(conexao);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 18);
            
            conexao = new ConexaoBinaria(true);
            
            // Barramento --(00)--> AX
            this.barramento.definirComoOrigem(conexao);            
            this.AX.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 19);
        }
        
        // BX
        {
            conexao = new ConexaoBinaria(true);
        
            // BX --(00)--> Barramento
            this.BX.definirComoOrigem(conexao);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 20);
            
            conexao = new ConexaoBinaria(true);
            
            // Barramento --(00)--> BX
            this.barramento.definirComoOrigem(conexao);            
            this.BX.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 21);
        }
        
        // CX
        {
            conexao = new ConexaoBinaria(true);
            
            // CX --(00)--> Barramento
            this.CX.definirComoOrigem(conexao);
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 22);
            
            conexao = new ConexaoBinaria(true);
            
            // Barramento --(00)--> CX
            this.barramento.definirComoOrigem(conexao);            
            this.CX.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 23);
        }
 
        // DX
        {
            conexao = new ConexaoBinaria(true);
            
            // DX --(00)--> Barramento
            this.DX.definirComoOrigem(conexao);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 24);

            conexao = new ConexaoBinaria(true);
            
            // Barramento --(00)--> DX
            this.barramento.definirComoOrigem(conexao);            
            this.DX.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 25);
        }
        
        
        // IR
        {
            conexao = new ConexaoBinaria(false);
            
            // IR --(00)--> Barramento
            this.IR.definirComoOrigem(conexao);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 12);

            conexao = new ConexaoBinaria(false);
            
            // Barramento --(00)--> IR
            this.barramento.definirComoOrigem(conexao);            
            this.IR.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 13);
            
            conexao = new ConexaoBinaria(false);
            
            // (IR)P1 --(00)--> Barramento
            this.IR.definirComoOrigem(conexao, 1);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 14);
            
            //conexao = new ConexaoBinaria(true);
            
            // Barramento --(00)--> (IR)P1
            //this.barramento.definirComoOrigem(conexao);            
            //this.IR.definirComoDestino(conexao, 1);
            //UC.registrarConexao(conexao, 15);
            
            conexao = new ConexaoBinaria(false);
            
            // (IR)P2 --(00)--> Barramento
            this.IR.definirComoOrigem(conexao, 2);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 15);
            
            //conexao = new ConexaoBinaria(true);
            
            // Barramento --(00)--> (IR)P2
            //this.barramento.definirComoOrigem(conexao);            
            //this.IR.definirComoDestino(conexao);
            //UC.registrarConexao(conexao, 15);
        }
        
        
        // PC
        this.PC = new Registrador(tamanhoPalavra);
        {
            conexao = new ConexaoBinaria(true);
            
            // PC --(00)--> Barramento
            this.PC.definirComoOrigem(conexao);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 16);
            
            conexao = new ConexaoBinaria(true);
            
            // Barramento --(00)--> PC
            this.barramento.definirComoOrigem(conexao);            
            this.PC.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 17);
        }
        
        
        // T
        {
            conexao = new ConexaoBinaria(true);
            
            // Barramento --(00)--> T
            this.T.definirComoOrigem(conexao);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 8);
            
            //conexao = new ConexaoBinaria(true);

            // T --(00)--> Barramento
            //this.barramento.definirComoOrigem(UC.obterConexao(0));            
            //this.T.definirComoDestino(UC.obterConexao(0));
            //UC.registrarConexao(conexao, 17);
        }
        
        // ULA
        {
            conexao = new ConexaoBinaria(true);
            
            // Barramento --(00)--> ULA
            this.ULA.definirComoOrigem(conexao);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 9);
        }
        
        
        // AC
        {
            conexao = new ConexaoBinaria(true);
            
            // (AC)P1 --(00)--> Barramento
            this.AC.definirComoOrigem(conexao, 0);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 10);

            // Barramento --(00)--> (AC)P1
            //this.barramento.definirComoOrigem(UC.obterConexao(0));            
            //this.AC.definirComoDestino(UC.obterConexao(0), 0);
            
            conexao = new ConexaoBinaria(true);
            
             // (AC)P2 --(00)--> Barramento
            this.AC.definirComoOrigem(conexao, 1);             
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 11);
            
            // Barramento --(00)--> (AC)P2
            //this.barramento.definirComoOrigem(UC.obterConexao(0));            
            //this.AC.definirComoDestino(UC.obterConexao(0), 1);
        }
        
        // MAR
        this.MAR = new Registrador(tamanhoPalavra);
        {
            conexao = new ConexaoBinaria(true);
            
            // MAR --(00)--> Barramento Externo
            this.MAR.definirComoOrigem(conexao);            
            barramentoExterno.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 5);
            
            conexao = new ConexaoBinaria(true);
            
            // Barramento Externo --(00)--> MAR
            barramentoExterno.definirComoOrigem(conexao);
            this.MAR.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 2);
        }
        
        // MBR
        this.MBR = new Registrador(tamanhoPalavra);
        {
            conexao = new ConexaoBinaria(false);
            
            // MBR --(00)--> Barramento
            this.MBR.definirComoOrigem(conexao);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 7);
            
            conexao = new ConexaoBinaria(false);

            // Barramento --(00)--> MBR
            this.barramento.definirComoOrigem(conexao);            
            this.MBR.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 6);
            
            conexao = new ConexaoBinaria(false);
            
            // MBR --(00)--> Barramento Externo
            this.MBR.definirComoOrigem(conexao);            
            barramentoExterno.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 3);
            
            conexao = new ConexaoBinaria(false);
            
            // Barramento Externo --(00)--> MBR
            barramentoExterno.definirComoOrigem(conexao);
            this.MBR.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 4);
        }
        
        {
            conexao = new ConexaoBinaria(false);
            
            // Barramento Externo --(00)--> RAM
            memoriaPrimaria.definirComoDestino(conexao);
            barramentoExterno.definirComoOrigem(conexao);
            UC.registrarConexao(conexao, 0);
            
            conexao = new ConexaoBinaria(false);
            
            // RAM --(00)--> Barramento Externo
            barramentoExterno.definirComoDestino(conexao);
            memoriaPrimaria.definirComoOrigem(conexao);
            UC.registrarConexao(conexao, 1);
        }
      
    }
    
    public void clock() {
        this.UC.executarCiclo();
        
        System.out.println("Status dos registradores: ");
        System.out.print("AX:  ");
        Teste.imprimir(AX.ler());
        System.out.print("\tBX:  ");
        Teste.imprimir(BX.ler());
        System.out.print("\nCX:  ");
        Teste.imprimir(CX.ler());
        System.out.print("\tDX:  ");
        Teste.imprimir(DX.ler());
        System.out.print("\nMAR: ");
        Teste.imprimir(MAR.ler());
        System.out.print("\tMBR: ");
        Teste.imprimir(MBR.ler());
        System.out.print("\nPC:  ");
        Teste.imprimir(PC.ler());
        System.out.print("\tIR:  ");
        Teste.imprimir(IR.ler(0));
        System.out.print(" | ");
        Teste.imprimir(IR.ler(1));
        System.out.print(" | ");
        Teste.imprimir(IR.ler(2));
        System.out.println();
    }
}
