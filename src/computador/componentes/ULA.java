package computador.componentes;

import computador.conexao.Conectavel;
import computador.conexao.ConexaoBinaria;
import static computador.base_numerica.Binario.valorInteiroComplementoDeDois;

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
    

    private boolean valorZero(int[] binario) {
        for(int bit : binario)
            if(bit == 1)
                return false;
        return true;
    }
    
    //Recebe um arranjo de int e devolve um arranjo com seu complemento de 2
    public static int[] complementoDeDois(int bin[]) {
        int complementoDeDois[] = new int[bin.length];

        //copia todos os zeros e o primeiro um
        int i = bin.length - 1;
        while (bin[i] == 0 && i > 0) {
            complementoDeDois[i] = bin[i];
            i--;
        }
        complementoDeDois[i] = bin[i];
        i--;

        // onde tem 0 coloca 1 onde tem 1 coloca 0 
        while (i >= 0) {
            if (bin[i] == 1) {
                complementoDeDois[i] = 0;
            } else {
                complementoDeDois[i] = 1;
            }
            i--;   
        }
        return complementoDeDois;
    }

    
    public boolean soma(){
        int[] num1 = operando1.ler();
        int[] num2 = segundoOperando.ler();
        int[] resultadoSoma = new int [16];
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
        }
        this.resultado.escrever(resultadoSoma, 1);
        this.flagZero = valorZero(resultado.ler(0));
        this.flagSinalPositivo = (!this.flagZero && resultado.lerBit(0) == 0);
                
        return true;
    }
    
 public boolean subtracao(){
        int[] num1 = operando1.ler();
        int[] num2 = segundoOperando.ler();
        int[] resultadoSoma;
        
        num2 = complementoDeDois(num2);
        
        resultadoSoma = soma(num1, num2);
        
        this.resultado.escrever(resultadoSoma, 1);     
        this.flagZero = valorZero(resultado.ler(0));
        this.flagSinalPositivo = (!this.flagZero && resultado.lerBit(0) == 0);
                
        return true;
    }
    
    
    
    public static int[] soma(int bin1[], int bin2[]) {
        int resultadoSoma[] = new int[16];
        int numeroDeBits;
        int sobe = 0;
        
        if(bin1.length <= bin2.length) numeroDeBits = bin1.length;
        else numeroDeBits = bin2.length;
        
        for (int i = numeroDeBits - 1; i >= 0; i--) {
            int bit1 = bin1[i];
            int bit2 = bin2[i];
            int soma = bit1 + bit2 + sobe;
            resultadoSoma[i] = soma % 2;
            sobe = soma / 2;
        }
        return resultadoSoma;
    }
    
    
    public static int[] subtrai(int bin1[], int bin2[]) {
        return soma(bin1, complementoDeDois(bin2));
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
                soma();
                return true;                    
                
            case 4: // somar (comp 2)
                soma();
                return true;
                
            case 5: // subtrair (comp 2)
                subtracao();
                return true;
                
            case 6: // multiplicar (comp 2)
                int[] mult1 = operando1.ler();
                int[] mult2 = segundoOperando.ler();
                int[] result = new int[32];
                
                int um = valorInteiroComplementoDeDois(mult1);
                int outro = valorInteiroComplementoDeDois(mult2);
                System.out.println("Prim: " + um + " seg: "+ outro);
                int resMult = um * outro;

                String strMult = Integer.toBinaryString(resMult);
                for (int x = 1; x <= strMult.length(); x++){
                    result[result.length - x] = Character.getNumericValue(strMult.charAt(strMult.length() - x));
                }
                
                result = complementoDeDois(result); //transforma novamente p/ complemento de 2
                this.resultado.escrever(result); //escreve o valor todo numa particao so
                return true;

            case 7: // dividir (comp 2)
                int[] num1 = operando1.ler();
                int[] num2 = segundoOperando.ler();
                int[] resto = new int[16];
                int[] valor = new int [16];
                int primeiro = valorInteiroComplementoDeDois(num1);
                int segundo = valorInteiroComplementoDeDois(num2);
                int valorDiv = primeiro / segundo;
                int restoDiv = primeiro % segundo;
                
                //Faz as transformações para binario novamente
                String strNum = Integer.toBinaryString(valorDiv);
                for (int x = 1; x <= strNum.length(); x++)
                    valor[valor.length - x] = Character.getNumericValue(strNum.charAt(strNum.length() - x));
                    
                String strResto = Integer.toBinaryString(restoDiv);
                for (int x = 1; x <= strResto.length(); x++)
                    resto[resto.length - x] = Character.getNumericValue(strResto.charAt(strResto.length() - x));
                //transforma novamente em complemento de 2
                valor = complementoDeDois(valor);
                resto = complementoDeDois(resto);
                
                this.resultado.escrever(valor,0); //escrever o resultado no P1
                this.resultado.escrever(resto,1);//escrever o resto no P2
                return true;
        }
        return false; 
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
