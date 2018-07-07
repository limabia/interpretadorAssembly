package computador.base_numerica;

/**
 * Esta classe oferece metodos para lidas com numeros binarios representados
 * por um vetor de inteiros.
 * 
 * Data 01-06-2018
 */
public class Binario {
    
    /**
     * Calcula o complemento de dois de um numero binario.
     * 
     * @param binario Vetor representando um numero binario em complemento de dois
     * @return Complemento de dois do numero binario indicado
     */
    public static int[] complementoDeDois(int[] binario) {
        int[] novoBinario = new int[binario.length];
        
        // Complemento de um
        for(int i = 0; i < novoBinario.length; i++)
            novoBinario[i] = (binario[i] + 1) % 2;
        
        // Soma um
        int excesso = 1;
        for(int i = binario.length - 1; i >= 0; i--) {
            novoBinario[i] = (binario[i] + excesso) / 2;
            excesso = (binario[i] + excesso) % 2;
        }
        
        return novoBinario;
    }
    
    /**
     * Converte um numero binario em complemento de dois em um numero inteiro decimal.
     * 
     * @param binario Numero binario em complemento de dois
     * @return Representacao decimal do numero em complemento de dois
     */
    public static int valorInteiroComplementoDeDois(int[] binario) {
        boolean negativo = (binario[0] == 1);
        
        // Caso o numero seja negativo eh necessario calcular o complemento de dois
        // para encontrar o valor em decimal
        if(negativo)
            binario = complementoDeDois(binario);
        
        // Calcula o valor inteiro absoluto do binario 
        int potencia = 1;
        int valor = 0;
        for(int i = binario.length - 1; i > 0; i--) {
            valor += potencia * binario[i];
            potencia *= 2;
        }

        // Multiplica o valor por -1, caso seja negativo
        if(negativo) valor *= -1;
        
        return valor;
    }
    
    /**
     * Converte um numero binario em decimal.
     * 
     * @param binario Numero binario
     * @return Valor decimal
     */
    public static int valorInteiro(int[] binario) {
        
        // Calcula o valor inteiro absoluto do binario 
        int potencia = 1;
        int valor = 0;
        for(int i = binario.length - 1; i >= 0; i--) {
            valor += potencia * binario[i];
            potencia *= 2;
        }
        
        return valor;
    }
    
    /*
     * Metodo para converter um objeto vetorintero de um numero binario em um numero decimal inteiro.
     * Esse metodo suporta conversor de no maximo 32 bits devido a variavel usada para converter o valor ser
     * um inteiro que possui 32 bits.
     */
    public static int binarioInteiroParaDecimal(int[] numero) {
        if(numero.length > 32)
            throw new IllegalArgumentException("O numero nao pode ter mais do que 32 bits");

        int potencia = 1;
        int soma = 0;
        for(int i = numero.length - 1; i > 0; i--) {
            soma += potencia * numero[i];
            potencia *= 2;
        }

        soma -= numero[0] * potencia;
        return soma;
    }	
    
    /*
     * Tranforma um numero inteiro no sistema decimal em um numero binario inteiro
     */
    public static int[] decimalInteiroParaInteiroBinario(int numero, int numeroBits) {
        boolean negativo = (numero < 0);

        // Transforma o numero em positivo
        if(negativo) numero *= -1;

        long expoente = 0;
        long potencia = 1;
        long soma = 1;
        while(soma < numero) {
            expoente++;
            potencia *= 2;
            soma += potencia;
        }

        if(expoente + 2 > numeroBits) if(!negativo) throw new IllegalArgumentException("Eh necessario mais bits do que o indicado para criar o numero");
        else if(potencia != numero) throw new IllegalArgumentException("Eh necessario mais bits do que o indicado para criar o numero");
        if(expoente + 2 > 32) throw new IllegalArgumentException("Nao eh possivel converter o numero " + numero + " para binario, eh necessario mais do que 32 bits");

        // Incrementa 1 para ajustar o vetor de acordo com o expoente e mais 1 para o bit de sinal
        int[] binario = new int[numeroBits];

        // Ajuda o bit de sinal
        binario[0] = (negativo ? 1 : 0);

        // Se for negativo armazena o binario em complemento de 2
        if(negativo) {
            // Insere os bits em complemento de 1
            for(int j = binario.length - 1; j >= 0; j--) {
                //binario[j] = ((numero % 2) == 1 ? 0 : 1); // Inverte o valor dos bits
                binario[j] = (int)((numero % 2) + 1) % 2; // Inverte o valor dos bits
                numero /= 2;
            }

            // Soma um no binario para virar complemento de dois
            //NumeroBinario.somarUm(binario, true);
            int anterior = 0;
            int excesso = 1;
            for(int i = binario.length - 1; i >= 0 && excesso == 1; i--) {
                anterior = binario[i]; // Salva o valor do bit antes da modificacao
                binario[i] = (binario[i] + excesso) % 2;
                excesso = (anterior + excesso) / 2; // Calcula o excesso usando o valor do bit antes da modificacao
            }
        } else {
            // Insere os bits
            for(int j = binario.length - 1; j > 0; j--) {
                binario[j] = (int)(numero % 2);
                numero /= 2;
            }
        }

        return (binario);
    }
}
