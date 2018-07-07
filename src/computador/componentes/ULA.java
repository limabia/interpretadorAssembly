package computador.componentes;

import static computador.base_numerica.Binario.decimalInteiroParaInteiroBinario;
import static computador.base_numerica.Binario.valorInteiro;
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
    private boolean flagSinalNegativo;
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
    
    public boolean sinalNegativo() {
        return this.flagSinalNegativo;
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
        for(int i = 1; i < binario.length; i++) {
            if(binario[i] == 1)
                return false;
        }
        return true;
    }
    
    public void somarUm(int[] binario) {
        int valorAnterior;
        int excesso = 1;
        for(int i = binario.length - 1; i >= 0 && excesso == 1; i--) {
            valorAnterior = binario[i];
            binario[i] = (binario[i] + excesso) % 2;
            excesso = (valorAnterior + excesso) / 2;
        }
    }
    
    public void complementoDeDois(int[] binario) {
        for (int i = 0; i < binario.length; i++) {
            binario[i] = (binario[i] + 1) % 2; // Inverte os sinais
        }
            
        this.somarUm(binario);
    }

    
    public void somar(int[] a, int[] b) {
        int[] c = new int[(a.length > b.length ? a.length : b.length)];

        // Excesso de uma soma (no caso de 1 + 1 excesso = 1)
        int excesso = 0;

        // Soma os bits
        for (int i = c.length - 1; i >= 0; i--) {
            c[i] = (a[i] + b[i] + excesso) % 2;
            excesso = (a[i] + b[i] + excesso) / 2;
        }
        
        // Se o numero 'a' e o numero 'b' possuem o mesmo sinal, entao houve um 'overflow' se, e somente se, 
        // o sinal do numero resultante da soma for diferente
        this.flagOverflow = (a[0] == b[0] && a[0] != c[0]);  
        this.resultado.escrever(c, 1);
        this.flagZero = valorZero(c);
        this.flagSinalPositivo = (!this.flagZero && c[0] == 0);
        this.flagSinalNegativo = (!this.flagZero && c[0] == 1);
    }
    
    public void subtrair(int[] a, int[] b) {
        this.complementoDeDois(b);
        somar(a, b);
        
        System.out.println("FALGS: Z S " + this.flagZero + " +" + this.flagSinalPositivo + " -" + this.flagSinalNegativo + "--------------------------------------------------------------");
    }
    
    public void multiplicar(int[] a, int[] b) {
        
        int sinal1 = 1;
        int sinal2 = 1;
        
        // Salva o sinal de A e tira o complemento de dois caso seja negativo
        if(a[0] == 1) {
            sinal1 = -1;
            this.complementoDeDois(a);
        }
        
        // Salva o sinal de B e tira o complemento de dois caso seja negativo
        if(b[0] == 1) {
            sinal2 = -1;
            this.complementoDeDois(b);
        }
        
        int valor1 = valorInteiro(a);
        int valor2 = valorInteiro(b);
        
        int[] resultado = decimalInteiroParaInteiroBinario(valor1 * valor2, 32);
        
        if(sinal1 * sinal2 < 0) {
            this.complementoDeDois(resultado);
        }
            
        this.resultado.escrever(resultado);
    }
    
    public void dividir(int[] a, int[] b) {
        
        int sinal1 = 1;
        int sinal2 = 1;
        
        // Salva o sinal de A e tira o complemento de dois caso seja negativo
        if(a[0] == 1) {
            sinal1 = -1;
            this.complementoDeDois(a);
        }
        
        // Salva o sinal de B e tira o complemento de dois caso seja negativo
        if(b[0] == 1) {
            sinal2 = -1;
            this.complementoDeDois(b);
        }
        
        int valor1 = valorInteiro(a);
        int valor2 = valorInteiro(b);
        
        int[] resultado = decimalInteiroParaInteiroBinario(valor1 / valor2, 16);
        int[] resto = decimalInteiroParaInteiroBinario(valor1 % valor2, 16);
        
        if(sinal1 * sinal2 < 0) {
            this.complementoDeDois(resultado);
        }
            
        this.resultado.escrever(resto, 0); 
        this.resultado.escrever(resultado, 1);
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
                
                this.somarUm(aux);
                
                this.resultado.escrever(aux, 1);
                //this.flagZero = valorZero(aux);
                //this.flagSinalPositivo = (!this.flagZero && aux[0] == 0);
                
                return true;    
                
            case 3: // somar
                //soma();
                return true;                    
                
            case 4: // somar (comp 2)
                this.somar(operando1.ler(), segundoOperando.ler());
                return true;
                
            case 5: // subtrair (comp 2)
                this.subtrair(operando1.ler(), segundoOperando.ler());
                return true;
                
            case 6: // multiplicar (comp 2)
                multiplicar(operando1.ler(), segundoOperando.ler());
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
                //valor = complementoDeDois(valor);
                //resto = complementoDeDois(resto);
                
                this.resultado.escrever(valor,1); //escrever o resultado no P2
                this.resultado.escrever(resto,0);//escrever o resto no P1
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
