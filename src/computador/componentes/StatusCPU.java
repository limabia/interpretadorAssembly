package computador.componentes;

/**
 *
 * @author marcelo
 */
public class StatusCPU {
        
    private Registrador AX, BX, CX, DX;
    private Registrador IR, PC;
    private Registrador T, AC;
    private Registrador MAR, MBR;
        
    private UC UC;
    private ULA ULA;
    private MemoriaPrimaria memoriaPrimaria;
    
    public StatusCPU(Registrador AX, Registrador BX, Registrador CX, Registrador DX,
            Registrador IR, Registrador PC, Registrador T, Registrador AC,
            Registrador MAR, Registrador MBR, UC UC, ULA ULA, MemoriaPrimaria memoriaPrimaria) {
    
        this.AX = AX;
        this.BX = BX;
        this.CX = CX;
        this.DX = DX;
        
        this.IR = IR;
        this.PC = PC;
        
        this.T = T;
        this.AC = AC;
        
        this.MAR = MAR;
        this.MBR = MBR;
        
        this.UC = UC;
        this.ULA = ULA;
        this.memoriaPrimaria = memoriaPrimaria;
    }
    
    public String getValorAX() {
        return AX.toString();
    }
    
    public String getValorBX() {
        return BX.toString();
    }
        
    public String getValorCX() {
        return CX.toString();
    }
    
    public String getValorDX() {
        return DX.toString();
    }
    
    
    public String getValorIR() {
        return IR.toString() + "   Flag ULA Zero: " + this.ULA.flagZero();
    }
    
    public String getValorPC() {
        return PC.toString() + "   Flag ULA Sinal Positivo: " + this.ULA.sinalPositivo();
    }
    
    
    public String getValorT() {
        return T.toString();
    }
    
    public String getValorAC() {
        return AC.toString();
    }
    
    
    public String getValorMAR() {
        return MAR.toString() + "    Flag ILA Sinal Negativo: " + this.ULA.sinalNegativo();
    }
    
    public String getValorMBR() {
        return MBR.toString();
    }
    
    public String getValorBUFFERMemoria() {
        return this.memoriaPrimaria.getValorBUFFER();
    }
    
    public String getEnderecoMemoria() {
        return this.memoriaPrimaria.getEndereco();
    }
    
    /**
     * Devolve a palavra de controle armazenada no MBR
     * 
     * @return Vetor de Strings com a representacao binaria de varios campos da palavra de controle
     */
    public String[] getPalavraControle() {
        return this.UC.obterStringPalavraControle();
    }
}
