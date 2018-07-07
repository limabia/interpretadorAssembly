package computador.componentes;

import computador.conexao.ConexaoBinaria;

/**
 *
 * Data 31-05-2018;
 */
public class CPU {
    
    String MICROPROGRAMA = "#########################################################################\n" +
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
"#11: ! flag para a leitura do endereco da operacao no Operando 1 do IR\n" +
"#\n" +
"#12: % endereco da microinstrucao em caso de jump\n" +
"#\n" +
"#\n" +
"# PADRAO:\n" +
"# FLAGS                     SINAIS DE CONTROLE\n" +
"# 0 0 0   0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0  $   @   I Z N P   & !   %\n" +
"#\n" +
"#########################################################################\n" +
"\n" +
"##########################################################################################\n" +
"##################################### CICLO DE BUSCA #####################################\n" +
"##########################################################################################\n" +
"######## 0 0 0 0 0 0 0 0 0  1 1 1 1 1 1 1 1 1 1  2 2 2 2 2 2 2 ###########################\n" +
"######## 1 2 3 4 5 6 7 8 9  0 1 2 3 4 5 6 7 8 9  0 1 2 3 4 5 6 ###########################\n" +
" 0 0 0   0 0 0 0 0 1 0 0 0  0 0 0 0 0 0 0 1 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 0 0   1 0 1 0 0 0 0 0 0  1 0 0 0 0 0 0 1 0 0  0 0 0 0 0 0 0   2   1   0 0 0 0  0 0   0\n" +
" 0 0 0   0 1 0 0 1 0 0 0 0  0 0 1 0 0 0 0 0 1 0  0 0 0 0 0 0 0   0   3   0 0 0 0  0 0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 1 0  0 0 0 0 1 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
"\n" +
"\n" +
"\n" +
"##########################################################################################\n" +
"#################################### CICLO DE EXECUCAO ###################################\n" +
"##########################################################################################\n" +
"######## 0 0 0 0 0 0 0 0 0  1 1 1 1 1 1 1 1 1 1  2 2 2 2 2 2 2 ###########################\n" +
"######## 1 2 3 4 5 6 7 8 9  0 1 2 3 4 5 6 7 8 9  0 1 2 3 4 5 6 ###########################\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  1 0   0\n" +
"\n" +
"\n" +
"\n" +
"##########################################################################################\n" +
"############################### INSTRUCOES DE MOVIMENTACAO ###############################\n" +
"##########################################################################################\n" +
"######## 0 0 0 0 0 0 0 0 0  1 1 1 1 1 1 1 1 1 1  2 2 2 2 2 2 2 ###########################\n" +
"######## 1 2 3 4 5 6 7 8 9  0 1 2 3 4 5 6 7 8 9  0 1 2 3 4 5 6 ###########################\n" +
"$ OPCODE   1  (MOV REG,CONST)\n" +
" 1 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 1 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE   2  (MOV REG,REG)\n" +
" 1 0 1   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE   3  (MOV REG,[CONST])\n" +
" 0 0 0   0 0 0 0 0 1 0 0 0  0 0 0 0 0 0 1 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 0 0   1 0 1 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   1   0 0 0 0  0 0   0\n" +
" 0 0 0   0 1 0 0 1 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   3   0 0 0 0  0 0   0\n" +
" 1 0 0   0 0 0 0 0 0 0 1 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE   4  (MOV REG,[REG])\n" +
" 0 0 1   0 0 0 0 0 1 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 0 0   1 0 1 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   1   0 0 0 0  0 0   0\n" +
" 0 0 0   0 1 0 0 1 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   3   0 0 0 0  0 0   0\n" +
" 1 0 0   0 0 0 0 0 0 0 1 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE   5  (MOV [REG],REG)\n" +
" 0 1 0   0 0 0 0 0 1 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 0 1   1 0 1 0 0 0 1 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   1   0 0 0 0  0 0   0\n" +
" 0 0 0   1 0 0 1 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   2   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE   6  (MOV [CONST],CONST)\n" +
" 0 0 0   0 0 0 0 0 1 0 0 0  0 0 0 0 0 1 0 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 0 0   1 0 1 0 0 0 1 0 0  0 0 0 0 0 0 1 0 0 0  0 0 0 0 0 0 0   0   1   0 0 0 0  0 0   0\n" +
" 0 0 0   1 0 0 1 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   2   1 0 0 0  0 0   0 \n" +
"\n" +
"##########################################################################################\n" +
"################################# INSTRUCOES ARITMETICAS #################################\n" +
"##########################################################################################\n" +
"######## 0 0 0 0 0 0 0 0 0  1 1 1 1 1 1 1 1 1 1  2 2 2 2 2 2 2 ###########################\n" +
"######## 1 2 3 4 5 6 7 8 9  0 1 2 3 4 5 6 7 8 9  0 1 2 3 4 5 6 ###########################\n" +
"\n" +
"$ OPCODE   7  (ADD REG,REG)\n" +
" 0 1 0   0 0 0 0 0 0 0 0 1  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 0 1   0 0 0 0 0 0 0 0 0  1 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   4   0   0 0 0 0  0 0   0\n" +
" 1 0 0   0 0 0 0 0 0 0 0 0  0 0 1 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE   8  (ADD REG,CONST)\n" +
" 0 1 0   0 0 0 0 0 0 0 0 1  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  1 0 0 0 0 0 1 0 0 0  0 0 0 0 0 0 0   4   0   0 0 0 0  0 0   0\n" +
" 1 0 0   0 0 0 0 0 0 0 0 0  0 0 1 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE   9  (SUB REG,REG)\n" +
" 0 0 1   0 0 0 0 0 0 0 0 1  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 1 0   0 0 0 0 0 0 0 0 0  1 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   5   0   0 0 0 0  0 0   0\n" +
" 1 0 0   0 0 0 0 0 0 0 0 0  0 0 1 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE  10  (SUB REG,CONST)\n" +
" 0 0 0   0 0 0 0 0 0 0 0 1  0 0 0 0 0 0 1 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 1 0   0 0 0 0 0 0 0 0 0  1 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   5   0   0 0 0 0  0 0   0\n" +
" 1 0 0   0 0 0 0 0 0 0 0 0  0 0 1 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE  11  (MUL REG)\n" +
" 0 0 0   0 0 0 0 0 0 0 0 1  0 0 0 0 0 0 0 0 0 1  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 1 0   0 0 0 0 0 0 0 0 0  1 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   6   0   0 0 0 0  0 0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 1 0 0 0 0 0 0 0  1 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE  12  (DIV REG)\n" +
" 0 1 0   0 0 0 0 0 0 0 0 1  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  1 0 0 0 0 0 0 0 0 1  0 0 0 0 0 0 0   7   0   0 0 0 0  0 0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 1 0 0 0 0 0 0 0  1 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 1 0 0 0 0 0 0 0 0  0 0 0 0 0 0 1   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE  13  (INC)\n" +
" 0 1 0   0 0 0 0 0 0 0 0 0  1 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   2   0   0 0 0 0  0 0   0\n" +
" 1 0 0   0 0 0 0 0 0 0 0 0  0 0 1 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"##########################################################################################\n" +
"################################### INSTRUCOES DE JUMP ###################################\n" +
"##########################################################################################\n" +
"######## 0 0 0 0 0 0 0 0 0  1 1 1 1 1 1 1 1 1 1  2 2 2 2 2 2 2 ###########################\n" +
"######## 1 2 3 4 5 6 7 8 9  0 1 2 3 4 5 6 7 8 9  0 1 2 3 4 5 6 ###########################\n" +
"\n" +
"$ OPCODE  14  (COMP AX,BX)    \n" +
" 0 0 1   0 0 0 0 0 0 0 0 1  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 1 0   0 0 0 0 0 0 0 0 0  1 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   5   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE  15  (COMP AX,CONST)\n" +
" 0 0 0   0 0 0 0 0 0 0 0 1  0 0 0 0 0 0 1 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 0  0 0   0\n" +
" 0 1 0   0 0 0 0 0 0 0 0 0  1 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   5   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE  16  (JE CONST) \n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 1 0 0  0 0   48\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 1 0 0 1 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE  17  (JNE CONST) \n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 0 1 1  0 0   51\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 1 0 0 1 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE  18  (JG CONST) \n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 0 0 1  0 0   54\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 1 0 0 1 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE  19  (JGE CONST) \n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 1 0 1  0 0   57\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 1 0 0 1 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE  20  (JL CONST) \n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 0 1 0  0 0   60\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 1 0 0 1 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"$ OPCODE  21  (JLE CONST) \n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   0 1 1 0  0 0   63\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0 0 0 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
" 0 0 0   0 0 0 0 0 0 0 0 0  0 0 0 0 0 1 0 0 1 0  0 0 0 0 0 0 0   0   0   1 0 0 0  0 0   0\n" +
"\n" +
"";
    
    private UC UC;
    
    private ULA ULA;
    
    private Barramento barramento;
    
    /* ########################### REGISTRADORES ########################### */
    
    // Proposito geral
    private Registrador AX, BX, CX, DX;
    
    // Execucao da instrucao
    private Registrador IR, PC;
    
    // Buffer para ULA
    private Registrador T, AC;
    
    // Se comunicam com o barramento externo
    private Registrador MAR, MBR;
    
    MemoriaPrimaria memoriaPrimaria;
    
    public CPU(int tamanhoPalavra, int tamanhoOP, int tamanhoParametro1,
            int tamanhoParametro2, Barramento barramentoExterno, 
            MemoriaPrimaria memoriaPrimaria) throws ComponentException {
    
        this.memoriaPrimaria = memoriaPrimaria;
        
        // Cria o barramento interno
        this.barramento = new Barramento(tamanhoPalavra);
        
        // Agrupa o barramento interno e externo em um vetor para salvar na UC
        Barramento[] barramentos = {barramentoExterno, this.barramento};
        
        this.AX = new Registrador(tamanhoPalavra);
        this.BX = new Registrador(tamanhoPalavra);
        this.CX = new Registrador(tamanhoPalavra);
        this.DX = new Registrador(tamanhoPalavra);
        
        this.IR = new Registrador(tamanhoOP, tamanhoParametro1, tamanhoParametro2);
        this.PC = new Registrador(tamanhoPalavra);
        
        this.T = new Registrador(tamanhoPalavra);
        this.AC = new Registrador(tamanhoPalavra, tamanhoPalavra);
        
        this.MAR = new Registrador(tamanhoPalavra);
        this.MBR = new Registrador(tamanhoPalavra);
        
        this.ULA = new ULA(tamanhoPalavra, T, AC);
        
        //String microprograma = (new java.io.File("").getAbsolutePath() + "/src/memoriaControle.txt");
        //System.out.println("---------------------------" + microprograma);
        //this.UC = new UC(memoriaPrimaria, this.ULA, barramentos, this.IR, 26, microprograma);
        this.UC = new UC(memoriaPrimaria, this.ULA, barramentos, this.IR, 26, MICROPROGRAMA);
        
        /* ############################ CONEXOES ############################ */
        
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
            
            conexao = new ConexaoBinaria(false);
            
            // (IR)P2 --(00)--> Barramento
            this.IR.definirComoOrigem(conexao, 2);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 15);

        }
        
        
        // PC
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
            this.barramento.definirComoOrigem(conexao);            
            this.T.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 8);
            
            System.out.println("CONEXAO EM QUESTAO: " + conexao);

        }
        
        // ULA
        {
            conexao = new ConexaoBinaria(true);
            
            // Barramento --(00)--> ULA
            this.barramento.definirComoOrigem(conexao);            
            this.ULA.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 9);
        }
        
        
        // AC
        {
            conexao = new ConexaoBinaria(true);
            
            // (AC)P1 --(00)--> Barramento
            this.AC.definirComoOrigem(conexao, 0);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 10);
            
            conexao = new ConexaoBinaria(true);
            
             // (AC)P2 --(00)--> Barramento
            this.AC.definirComoOrigem(conexao, 1);             
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 11);
            
        }
        
        // MAR
        {
            conexao = new ConexaoBinaria(true);
            
            // MAR --(00)--> Barramento Externo
            this.MAR.definirComoOrigem(conexao);            
            barramentoExterno.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 2);
            
            conexao = new ConexaoBinaria(true);
            
            // Barramento --(00)--> MAR
            this.barramento.definirComoOrigem(conexao);
            this.MAR.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 5);
        }
        
        // MBR
        {
            conexao = new ConexaoBinaria(true);
            
            // MBR --(00)--> Barramento
            this.MBR.definirComoOrigem(conexao);            
            this.barramento.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 7);
            
            conexao = new ConexaoBinaria(true);

            // Barramento --(00)--> MBR
            this.barramento.definirComoOrigem(conexao);            
            this.MBR.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 6);
            
            conexao = new ConexaoBinaria(true);
            
            // MBR --(00)--> Barramento Externo
            this.MBR.definirComoOrigem(conexao);            
            barramentoExterno.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 3);
            
            conexao = new ConexaoBinaria(true);
            
            // Barramento Externo --(00)--> MBR
            barramentoExterno.definirComoOrigem(conexao);
            this.MBR.definirComoDestino(conexao);
            UC.registrarConexao(conexao, 4);
        }
        
        {
            conexao = new ConexaoBinaria(true);
            
            // Barramento Externo --(00)--> RAM
            memoriaPrimaria.definirComoDestino(conexao);
            barramentoExterno.definirComoOrigem(conexao);
            UC.registrarConexao(conexao, 0);
            
            conexao = new ConexaoBinaria(true);
            
            // RAM --(00)--> Barramento Externo
            barramentoExterno.definirComoDestino(conexao);
            memoriaPrimaria.definirComoOrigem(conexao);
            UC.registrarConexao(conexao, 1);
            
        }
      
    }
    
    /**
     * Executa um ciclo de clock
     */
    public boolean clock() {
        return this.UC.executarCiclo();
    }
    
    /**
     * Devovle um objeto StatusCPU para fazer o monitoramento dos registradores
     * da CPU e de seus componentes.
     * 
     * @return Objeto StatusCPU
     */
    public StatusCPU getStatusCPU() {
        return new StatusCPU(this.AX, this.BX, this.CX, this.DX, this.IR, this.PC, 
                this.T, this.AC, this.MAR, this.MBR, this.UC, this.ULA, this.memoriaPrimaria);
    }
}
