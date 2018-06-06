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
        for(int i = binario.length; i >= 0; i++) {
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
    public static int valorInteiro(int[] binario) {
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
    
}
