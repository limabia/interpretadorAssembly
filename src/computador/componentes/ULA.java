package computador.componentes;

import computador.conexao.Conectavel;
import computador.conexao.ConexaoBinaria;

/**
 *
 * Data: 31-05-2018
 */
public class ULA implements Conectavel {
    
    //private final Registrador operando1;
    private Registrador operando1;
    
    /* Registradores de entrada e saida */
    private Registrador segundoOperando; // Registrador com o segundo operando
    private Registrador resultado; // Registrador para armazenar o resultado
    
    /* Flags */
    private boolean flagZero; // Resultado da operacao deu igual a zero
    private boolean flagSinalPositivo; // Se resultado eh positivo ou nao
    private boolean flagOverflow; // Operacao resultou em overflow


    
    /**
     * 
     * @param tamanhoOperando
     * @param segundoOperando
     * @param resultado 
     */
    public ULA(int tamanhoOperando, Registrador segundoOperando, Registrador resultado) {
        if(segundoOperando.obterTamanho() != tamanhoOperando || resultado.obterTamanho() != 2 * tamanhoOperando)
            throw new IllegalArgumentException("Nao eh possivel criar a ULA: "
                + "o tamanho dos registradores e da entrada nao estao adequados");
        
        if(resultado.numeroParticoes() != 2)
            throw new IllegalArgumentException("O Registrador de saida deve ter exatamente duas particoes");
        
        if(tamanhoOperando <= 0)
            throw new IllegalArgumentException("O tamanho dos operandos da ULA devem ser maiores do que 0");
        
        this.operando1 = new Registrador(tamanhoOperando);
        
        this.segundoOperando = segundoOperando;
        this.resultado = resultado;
        
        this.flagZero = false;
        this.flagSinalPositivo = false;
        this.flagOverflow = false;
        
    }
    
    
    
    /**
     * Verifica se um codigo de uma operacao eh valido.
     * 
     * @param codigoOperacao Codigo da operacao
     * @return 'true' caso o codigo seja valido, 'false' caso nao seja.
     */
    public static boolean codigoValido(int codigoOperacao) {
        return (codigoOperacao >= 0 && codigoOperacao <= 7);
    }
    
    
    
    /**
     * Retorna o valor da flag de resultado zero.
     * 
     * @return 'true' se o ultimo resultado for zero, 'false' caso contrario.
     */
    public boolean flagZero() {
        return this.flagZero;
    }
    
    /**
     * Retorna o valor da flag de sinal positivo.
     * 
     * @return 'true' caso o ultimo resultado tenha sido positivo, 'false' caso
     *         contrario.
     */
    public boolean sinalPositivo() {
        return this.flagSinalPositivo;
    }
    
    /**
     * Retorna o valor da flag de overflow.
     * 
     * @return 'true' caso a ultima operacao tenha dado overflow, 'false' caso
     *         contrario.
     */
    public boolean flagOverflow() {
        return this.flagOverflow;
    }
    
    
    /**
     * @param operando
     * @return o registrador com o valor armazenado em complemento de dois.
    */
    public Registrador obterComplemento(Registrador operando){
        int[] normal = operando.ler();
        int[] complementoDeDois = new int[32];
        // onde tem 0 coloca 1 onde tem 1 coloca 0 
        for (int i = 0; i < normal.length; i++) {
            if (normal[i] == 1) {
                complementoDeDois[i] = 0;
            } else if (normal[i] == 0) {
                complementoDeDois[i] = 1;
            }
            System.out.println("Complemento: " + complementoDeDois[i]);
        }
        
        Registrador Um = new Registrador(1);
        Um.escrever(new int[] {1});
        operando.escrever(complementoDeDois);
        operando = soma(operando, Um);
       
        return operando;
    }
    
    
    
    public Registrador soma(Registrador operando, Registrador segundoOperando){
        int[] num1 = operando.ler();
        int[] num2 = segundoOperando.ler();
        int[] resultadoSoma = new int [32];
        int sobe = 0;
        int tam;
        
        if(num1.length <= num2.length) tam = num1.length;
        else tam = num2.length;
        
        for (int i = tam-1; i >= 0; i--){
            int bit1 = num1[i];
            int bit2 = num2[i];
            int soma = bit1 + bit2 + sobe;
            resultadoSoma[i] = soma % 2;
            sobe = soma / 2;
            System.out.println("Resultado Soma: " + resultadoSoma[i]);
        }
        this.resultado.escrever(resultadoSoma);
               
        this.flagZero = valorZero(resultado.ler(0));
        this.flagSinalPositivo = (!this.flagZero && resultado.lerBit(0) == 0);
                
        return resultado;
    }
    /**
     * Dado um codigo de operacao e um operando realiza uma operacao com o
     * primeiroOPerando e com o valor armazenado no Registrador segundoParametro
     * (caso seja uma operacao binaria - dois parametros) e salva o resultado no
     * Registrador resultado.
     *
     * Os operandos podem estar em complemento de dois ou nao, depende da
     * operacao escolhida.
     * 
     * @param codigoOperacao Codigo da operacao a ser executada
     * @return 'true' caso seja possivel realizar a operacao, 'false' caso nao
     *         seja.
     */
    public boolean operar(int codigoOperacao) {
        codigoOperacao = 5;
        segundoOperando = new Registrador(5);
        operando1 = new Registrador(5);
        operando1.escrever(new int[] {0,0,0,0,1});
        segundoOperando.escrever(new int[] {0,0,0,1,1});
        
        switch(codigoOperacao) {
            case 0:
                // Nao faz nada
                return true;
            
            case 1: // incrementar
                
                return true;
            
            case 2: // incrementar (comp 2)
                
                // Verifica o bit de sinal antes da operacao
                int sinal = operando1.lerBit(0);
                
                int[] aux = operando1.ler();
                
                int valorAnterior;
                int excesso = 1;
                for(int i = aux.length - 1; i >= 0 && excesso == 1; i--) {
                    valorAnterior = aux[i];
                    aux[i] = (aux[i] + excesso) % 2;
                    excesso = (valorAnterior + excesso) / 2;
                }
                
                this.resultado.escrever(aux, 0);
                
                this.flagZero = valorZero(resultado.ler(0));
                this.flagSinalPositivo = (!this.flagZero && resultado.lerBit(0) == 0);
                
                return true;    
                
            case 3: // somar
                soma(operando1, segundoOperando);
                
                return true;    
                
                
            case 4: // somar (comp 2)
                operando1 = obterComplemento(operando1);
                segundoOperando = obterComplemento(segundoOperando);
                soma(operando1, segundoOperando);
                return true;
                
            case 5: // subtrair (comp 2)
                segundoOperando = obterComplemento(segundoOperando);
                soma(operando1, segundoOperando);
                return true;
                
            case 6: // multiplicar (comp 2)
                
                return true;
                
            case 7: // dividir (comp 2)
                
                return true;
                
            default:
            
        }
        
        return false; 
    }
    
    private boolean valorZero(int[] binario) {
        for(int bit : binario)
            if(bit == 1)
                return false;
        return true;
    }
    
    /**
     * Define o Registrador interno como destino de um objeto ConexaoBinaria.
     * 
     * @param conexao Conexao a ter o Registrador interno como destino
     * @return 'true' caso tenha sido possivel definir o Registrador interno como
     *         destino, ou false caso nao tenha sido.
     */
    @Override
    public boolean definirComoDestino(ConexaoBinaria conexao) {
        return this.operando1.definirComoDestino(conexao);
    }
    
    /**
     * Define o Registrador interno como origem de um objeto ConexaoBinaria.
     * 
     * @param conexao Conexao a ter o Registrador interno como origem
     * @return 'true' caso tenha sido possivel definir o Registrador interno como
     *         origem, ou false caso nao tenha sido.
     */
    @Override
    public boolean definirComoOrigem(ConexaoBinaria conexao) {
        return this.operando1.definirComoOrigem(conexao);
    }
    
}
