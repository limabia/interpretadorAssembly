package computador.componentes;

import computador.conexao.ConexaoBinaria;
import java.util.Arrays;

/**
 *
 * Data: 31-05-2018
 */
public class UC {
    
    private final RAM RAM;
    
    private final ULA ULA;
    
    private final Barramento barramento;
    
    private final ConexaoBinaria[] conexoes;
    
    private final Registrador IR;
    private final Registrador PC;
    
    private final Firmware firmware;
    
    public UC(RAM RAM, ULA ULA, Barramento barramento, ConexaoBinaria[] conexoes,
            Registrador IR, Registrador PC, String microprograma) throws ComponentException {
    
        this.RAM = RAM;
        this.ULA = ULA;
        this.barramento = barramento;
        this.conexoes = conexoes;
        this.IR = IR;
        this.PC = PC;
        
        try {
            this.firmware = new Firmware(microprograma);
        } catch(FirmwareException fe) {
            throw new ComponentException("Nao eh possivel criar a UC: " + fe.getMessage());
        }
    }
    
    /**
     * Registra uma conexao no vetor de conexoes.
     * 
     * @param conexao Conexao a ser registrada
     * @param indice Indice no qual a conexao sera registrada
     * @return 'true' caso seja possivel registrar a conexao, 'false' caso ja
     *         exista uma conexao naquela posicao
     */
    public boolean registrarConexao(ConexaoBinaria conexao, int indice) {
        if(indice < 0 || indice >= this.conexoes.length)
            throw new IllegalArgumentException("Indice invalido, a UC nao pode registrar a conexao");
        
        if(this.conexoes[indice] != null)
            return false;
        
        this.conexoes[indice] = conexao;
        return true;
    }
    
    public ConexaoBinaria obterConexao(int posicao) {
        return this.conexoes[posicao];
    }
    
    /**
     * Transforma o OPCode em binario em um endereco para a proxima
     * microinstrucao a ser executada.
     * 
     * @return Endereco da proxima microinstrucao a ser executada
     */
    private int decoderOpcode(int[] binario) {
        //TODO
        return -1;
    }
    
    public void iniciarCiclo() {
        int CAR; // Control Address Register
        int[] CBR = new int[Firmware.TAMANHO_PALAVRA_CONTROLE]; // Control Buffer Register
        
        // Transforma o opcode da isntrucao do IR em um endereco do microprograma
        CAR = decoderOpcode(this.IR.ler(0));
        
        // Indices das portas dos Registradores operandos
        int portaEntraP1 = -1;
        int portaSaidaP1 = -1;
        int portaSaidaP2 = -1;
        
        while(true) {
            CBR = this.firmware.ler(CAR);
            
            if(CBR[Firmware.INDICE_FLAG_ENTRADA_P1] == 1)
                CBR[portaEntraP1] = 1;
            
            if(CBR[Firmware.INDICE_FLAG_SAIDA_P1] == 1)
                CBR[portaSaidaP1] = 1;
            
            if(CBR[Firmware.INDICE_FLAG_SAIDA_P2] == 1)
                CBR[portaSaidaP2] = 1;
            
            for(int i = Firmware.INDICE_SINAIS_CONTROLE; i < Firmware.INDICE_SINAIS_CONTROLE + Firmware.NUMERO_SINAIS_CONTROLE; i++)
                if(CBR[i] == 1)
                    this.conexoes[i - Firmware.NUMERO_SINAIS_CONTROLE].abrir();
            
            // Interpretar sinais de ULA e da Memoria
            
            // Dispara a movimentacao dos dados
            barramento.conectar();
            
            // Calcular proximo endereco
            
            // Determinar condicao de parada para o loop
        }
        
        
    }
    
}