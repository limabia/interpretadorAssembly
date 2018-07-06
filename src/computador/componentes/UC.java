package computador.componentes;

import computador.Teste;
import computador.base_numerica.Binario;
import computador.firmware.FirmwareException;
import computador.firmware.Firmware;
import computador.conexao.ConexaoBinaria;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.Arrays;

/**
 *
 * Data: 31-05-2018
 */
public class UC {
    
    private final MemoriaPrimaria memoriaPrimaria;
    
    private final ULA ULA;
    
    private final ArrayList<Barramento> barramentos;
    
    private final ConexaoBinaria[] conexoes;
    
    private final Registrador IR;
    
    private final Firmware firmware;
    
    // Control Address Register
    private int CAR = 0;
    
    // Control Buffer Register
    private PalavraControle CBR;
    
    // Portas dos paramentros da intrucao no IR
    private int portaEntradaP1;
    private int portaSaidaP1;
    private int portaSaidaP2;
    
    public UC(MemoriaPrimaria memoriaPrimaria, ULA ULA, Barramento[] barramentos,
            Registrador IR, int numeroConexoes, String microprograma) throws ComponentException {
    
        this.memoriaPrimaria = memoriaPrimaria;
        this.ULA = ULA;
        this.IR = IR;
        
        this.barramentos = new ArrayList();
        for(Barramento barramento : barramentos)
            this.barramentos.add(barramento);
        
        this.conexoes = new ConexaoBinaria[numeroConexoes];
        
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
        if(indice < 0)
            throw new IllegalArgumentException("Indice invalido, a UC nao pode registrar a conexao");
        
        if(this.conexoes[indice] != null)
            return false;
        
        this.conexoes[indice] = conexao;
        return true;
    }
    
    public ConexaoBinaria obterConexao(int indice) {
        return this.conexoes[indice];
    }
    
    public int portaDecoder(int porta, boolean entrada) {
        switch (porta) {
    
            case 19: // AX
                if(entrada)
                    return 19;
                else
                    return 18;
            
            case 21: // BX
                if(entrada)
                    return 21;
                else
                    return 20;
                
            case 23: // CX
                if(entrada)
                    return 23;
                else
                    return 22;
                
            case 25: // DX
                if(entrada)
                    return 25;
                else
                    return 24;
            
            default:
                return -1;
        }
    }
    
    public void executarCiclo() {
    
        /*for(int i = 0; i < this.conexoes.length; i++) {
            System.out.println(i + "\t" + this.conexoes[i]);
            // APAGAR DEBUG DPS
        }*/
        
        // Le uma palavra de controle
        this.CBR = this.firmware.ler(this.CAR);
        
        if(this.CBR.jumpEntradaP1())
            this.CBR.sinalDeControle(this.portaEntradaP1, true);
          
        if(this.CBR.jumpSaidaP1())
            this.CBR.sinalDeControle(this.portaSaidaP1, true);
        
        if(this.CBR.jumpSaidaP2())
            this.CBR.sinalDeControle(this.portaSaidaP2, true);

        // Abre as postas
        for(int i = 0; i < this.conexoes.length; i++)
            if(this.CBR.sinalDeControle(i))
                this.conexoes[i].abrir();
        
        // Faz o envio de dados
        for(Barramento barramento : this.barramentos)
            barramento.conectar();
        
        // Interpreta os sinais da ULA e da RAM
        this.ULA.operar(this.CBR.operacaoULA());
        this.memoriaPrimaria.operar(this.CBR.operacaoRAM());
        
        if(this.CBR.lerIR()) {
            //System.out.println(this.IR == null);
            int enderecoInstrucao = Binario.valorInteiro(this.IR.ler(0));
            this.CBR.enderecoJump(this.firmware.enderecoInstrucao(enderecoInstrucao));
            
            this.portaEntradaP1 = portaDecoder(Binario.valorInteiro(this.IR.ler(1)), true);
            this.portaSaidaP1 = portaDecoder(Binario.valorInteiro(this.IR.ler(1)), false);
            this.portaSaidaP2 = portaDecoder(Binario.valorInteiro(this.IR.ler(2)), false);
            
        }
            
        if(this.CBR.jumpIncondicional() || this.CBR.jumpZero() || 
                this.CBR.jumpNegativo() || this.CBR.jumpPositivo())
            this.CAR = this.CBR.enderecoJump();
        else
            this.CAR++;
        
    }
    
    /*public void iniciarCiclo() {
        int CAR; // Control Address Register
        int[] CBR = null; // Control Buffer Register
        
        // Transforma o opcode da isntrucao do IR em um endereco do microprograma
        CAR = decoderOpcode(this.IR.ler(0));
        
        // Indices das portas dos Registradores operandos
        int portaEntraP1 = -1;
        int portaSaidaP1 = -1;
        int portaSaidaP2 = -1;
        
        // 0 - Busca
        // 1 - Indirecao
        // 2 - Execucao
        int ICC = 0;
        
        while(CAR > 0) {
               
            // Calcula proximo endereco
            {
                if(CBR[0] == 1)
                    CAR = decoderOpcode(this.IR.ler(0));
                
                else if(CBR[Firmware.INDICE_JUMP_INCONDICIONAL] == 1)
                    CAR = CBR[Firmware.INDICE_ENDERECO_JUMP];
                
                else if(CBR[Firmware.INDICE_JUMP_OVERFLOW] == 1 && this.ULA.flagOverflow())
                    CAR = CBR[Firmware.INDICE_ENDERECO_JUMP];
                
                else if(CBR[Firmware.INDICE_JUMP_ZERO] == 1 & this.ULA.flagZero())
                    CAR = CBR[Firmware.INDICE_ENDERECO_JUMP];
                
                else
                    CAR++;
                    
            }
            
            CBR = this.firmware.ler(CAR);
            
            if(CBR[Firmware.INDICE_FLAG_ENTRADA_P1] == 1)
                CBR[portaEntraP1] = 1;
            
            if(CBR[Firmware.INDICE_FLAG_SAIDA_P1] == 1)
                CBR[portaSaidaP1] = 1;
            
            if(CBR[Firmware.INDICE_FLAG_SAIDA_P2] == 1)
                CBR[portaSaidaP2] = 1;
            
            if(CBR[Firmware.INDICE_SINAIS_MEMORIA] == 2) // Ler
                this.RAM.ler();
            
            for(int i = Firmware.INDICE_SINAIS_CONTROLE; i < Firmware.INDICE_SINAIS_CONTROLE + Firmware.NUMERO_SINAIS_CONTROLE; i++)
                if(CBR[i] == 1)
                    this.conexoes[i - Firmware.NUMERO_SINAIS_CONTROLE].abrir();
            
            // Interpreta os sinais da ULA
            this.ULA.operar(CBR[Firmware.INDICE_SINAIS_ULA]);
            
            this.RAM.operar(CBR[Firmware.INDICE_SINAIS_MEMORIA]);
            
            // Dispara a movimentacao dos dados
            barramento.conectar();
            
            // Calcula proximo endereco
            {
                if(CBR[Firmware.INDICE_JUMP_INCONDICIONAL] == 1)
                    CAR = CBR[Firmware.INDICE_ENDERECO_JUMP];
                
                else if(CBR[Firmware.INDICE_JUMP_OVERFLOW] == 1 && this.ULA.flagOverflow())
                    CAR = CBR[Firmware.INDICE_ENDERECO_JUMP];
                
                else if(CBR[Firmware.INDICE_JUMP_ZERO] == 1 & this.ULA.flagZero())
                    CAR = CBR[Firmware.INDICE_ENDERECO_JUMP];
                
                else
                    CAR++;
                    
            }
            
        }
    }*/
    
}