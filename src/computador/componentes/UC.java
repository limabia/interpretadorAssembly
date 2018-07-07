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
        
        // Verifica as condicoes de jump
        if(this.CBR.jumpEntradaP1())
            this.CBR.sinalDeControle(this.portaEntradaP1, true);
            //this.conexoes[this.portaEntradaP1].abrir();
            
        if(this.CBR.jumpSaidaP1())
            this.CBR.sinalDeControle(this.portaSaidaP1, true);
            //this.conexoes[this.portaSaidaP1].abrir();
            
        if(this.CBR.jumpSaidaP2())
            this.CBR.sinalDeControle(this.portaSaidaP2, true);
            //this.conexoes[this.portaSaidaP2].abrir();
            
        // Se for uma operacao de leitura eh necessario executar antes
        if(this.CBR.operacaoRAM() == this.memoriaPrimaria.codigoOperacaoLer())
            this.memoriaPrimaria.operar(this.CBR.operacaoRAM());
        
        // Abre as postas
        for(int i = 0; i < this.conexoes.length; i++)
            if(this.CBR.sinalDeControle(i)) {
                System.out.print((i+1) + "(" + i +"), ");
                this.conexoes[i].abrir();
            }
        System.out.println(this.CBR.jumpEntradaP1() + " " + this.CBR.jumpSaidaP1() + " " + this.CBR.jumpSaidaP2() + "\n");
        System.out.println(this.portaEntradaP1 + " " + this.portaSaidaP1 + " " + this.portaSaidaP2 + "\n");
        
        // Faz o envio de dados
        for(Barramento barramento : this.barramentos)
            barramento.conectar();
        
        // Verifica as condicoes de jump
        if(this.CBR.jumpEntradaP1())
            this.CBR.sinalDeControle(this.portaEntradaP1, false);

        if(this.CBR.jumpSaidaP1())
            this.CBR.sinalDeControle(this.portaSaidaP1, false);
            
        if(this.CBR.jumpSaidaP2())
            this.CBR.sinalDeControle(this.portaSaidaP2, false);
        
        // Interpreta os sinais da ULA e da RAM
        this.ULA.operar(this.CBR.operacaoULA());
        
        // Se nao for uma operacao de leitura eh necessario executar depois
        if(this.CBR.operacaoRAM() != this.memoriaPrimaria.codigoOperacaoLer())
            this.memoriaPrimaria.operar(this.CBR.operacaoRAM());
        
        // Define o endereco de JUMP de acordo com o OPCODE
        if(this.CBR.lerIR()) {
            int enderecoInstrucao = Binario.valorInteiro(this.IR.ler(0));
            this.CBR.enderecoJump(this.firmware.enderecoInstrucao(enderecoInstrucao));
            
            this.portaEntradaP1 = portaDecoder(Binario.valorInteiro(this.IR.ler(1)), true);
            this.portaSaidaP1 = portaDecoder(Binario.valorInteiro(this.IR.ler(1)), false);
            this.portaSaidaP2 = portaDecoder(Binario.valorInteiro(this.IR.ler(2)), false);
        }
        
        // Define o endereco de JUMP igual ao parametro 1 no IR
        if(this.CBR.lerIRP1())
            this.CBR.enderecoJump(Binario.valorInteiro(this.IR.ler(1)));
            
        // ARRUMAR ESSES JUMPS PARA CHECAREM A ULA
        if(this.CBR.jumpIncondicional())
            this.CAR = this.CBR.enderecoJump();
        else if(this.CBR.jumpZero() && this.ULA.flagZero()) {
            this.CAR = this.CBR.enderecoJump();
        } else if(this.CBR.jumpNegativo() && this.ULA.sinalNegativo()) {
             this.CAR = this.CBR.enderecoJump();   
        } else if(this.CBR.jumpPositivo() && this.ULA.sinalPositivo()) {
            this.CAR = this.CBR.enderecoJump();
        } else
            this.CAR++;
    }
    
    /**
     * Devolve um vetor de bits com os elementos da palavra de controles agrupados
     * em Strings, por exemplo, todos os sinais de controle das portas sao colocados
     * juntos, enquanto as flags separadas.
     * 
     * @return Vetor de Strings contendo os bits da palavra de controle
     */
    public String[] obterStringPalavraControle() {
        return this.CBR.obterString();
    }
}