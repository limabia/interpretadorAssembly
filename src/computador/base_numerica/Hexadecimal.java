package computador.base_numerica;

/*
 * Data 27-06-2018
 */
public class Hexadecimal {
    /**
     * Converte um numero em base hexadecimal representado por uma string em um
     * binario representado por vetor de inteiros
     * 
     * @param hexadecimal Numero hexadecimal que sera convertido
     * @return Conversão do numero em binario
     */
    public static int[] hexaParaBinario (String hexadecimal){
        
        int hexa;
        hexa = Integer.parseInt(hexadecimal, 16); 
        String bin;
        bin = Integer.toBinaryString(hexa); 

        char [] binarioArray = bin.toCharArray();

        int [] binario = new int[bin.length()];
        
        for (int i = 0; i < binario.length; i++){ 
            binario[i] = Integer.parseInt(String.valueOf(binarioArray[i]));
        }
        
        return binario;
        
    }
}
