package computador.componentes;

import computador.conexao.ConexaoBinaria;

/**
 *
 * Data: 31-05-2018
 */
public class UC {
    
    // VALIDAR ESSE ENDERECO DEPOIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private final int TAMANHO_ENDERECO_CONTROLE = 8;
    
    private final RAM RAM;
    
    private final ULA ULA;
    
    private final ConexaoBinaria[] conexoes;
    
    private final Registrador IR;
    private final Registrador PC;
    
    private final Registrador CAR; // Control Address Register
    private final Registrador CBR; // Control Buffer Register
    
    public UC(RAM RAM, ULA ULA, ConexaoBinaria[] conexoes, Registrador IR,
            Registrador PC) {
    
        this.RAM = RAM;
        this.ULA = ULA;
        this.conexoes = conexoes;
        this.IR = IR;
        this.PC = PC;
        
        this.CAR = new Registrador(this.TAMANHO_ENDERECO_CONTROLE);
        this.CBR = new Registrador(this.TAMANHO_ENDERECO_CONTROLE);
        
    }
    
    /**
     * Transforma o OPCode armazenado no IR em um endereco para a proxima
     * microinstrucao a ser executada.
     * 
     * @return Endereco da proxima microinstrucao a ser executada
     */
    private int decoderOpcode() {
        //TODO
        return -1;
    }
    
    /*
    
     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
              !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                          !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                               !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    LEMBRAR DE QUE: AS PORTAS DE SAIDA SAO ABERTAS PRIMEIRO E DEPOIS AS DE ENTRADA SAO ABERTAS
    ISTO EH EXTREMAMENTE IMPORTANTE PARA O FUNCIONAMENTO DO PROGRAMA POIS ABRIR UMA PORTA 
    DE ENTRADA PRIMEIRO E DEPOIS UMA DE SAIDA NAO PERMITE O FLUXO DOS DADOS, REPITO, NAO
    PERMITE O FLUXO DOS DADOS.
    
    */
    
    public ConexaoBinaria obterConexao(int posicao) {
        return this.conexoes[posicao];
    }
    
}