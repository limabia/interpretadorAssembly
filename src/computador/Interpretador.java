package computador;

import computador.base_numerica.Hexadecimal;

/**
 *
 * @author bianca
 */
public class Interpretador {
    
    public int[][] compilaBin(String[][] codigoHexa) {
        int n = codigoHexa.length;
        int[][] compilado = new int[n][16];
        
        for (int i=0; i<n; i++) {
            String[] linha = codigoHexa[i];
            int[] comando = Hexadecimal.hexaParaBinario(linha[0]);
            
            for (int j=0; j<comando.length; j++) {
                int dif = 5 - comando.length;
                compilado[i][j + dif] = comando[j];
            }
            
            if (linha[1] != null) {
                int[] param1 = Hexadecimal.hexaParaBinario(linha[1]);
                if (param1.length > 5)
                    throw new RuntimeException("Memory Overflow");
                for (int j=0; j<param1.length; j++) {
                    int dif = 5 - param1.length;
                    compilado[i][j + dif + 5] = param1[j];
                }
            }
            
            if (linha[2] != null) {
                int[] param2 = Hexadecimal.hexaParaBinario(linha[2]);
                if (param2.length > 6)
                    throw new RuntimeException("Memory Overflow");
                for (int j=0; j<param2.length; j++) {
                    int dif = 6 - param2.length;
                    compilado[i][j + dif + 10] = param2[j];
                }
            }
            
        }
            
        return compilado;
    }
    
    
    public String[][] compilaHexa(String instrucoes){
        String[] linhas = instrucoes.split("\r\n|\r|\n");
        int n = linhas.length;
        String[][] compiladoHexa = new String[n][3];
        
        for (int i=0; i<linhas.length; i++){
            String linhaAtual = linhas[i];
            String[] comandoAtual = linhaAtual.split(" |,");
            String instrucao = mapeiaInstrucao(comandoAtual);
            compiladoHexa[i][0] = instrucao;
                    
            // traduz parametros
            if (comandoAtual.length > 1) {
                compiladoHexa[i][1] = traduzParametro(comandoAtual[1]);
            }
            if (comandoAtual.length > 2) {
                compiladoHexa[i][2] = traduzParametro(comandoAtual[2]);
            }
        }
        return compiladoHexa;
    }
    
    public int[][] compila(String codigo) {
        String[][] codigoHexa = this.compilaHexa(codigo);
        return this.compilaBin(codigoHexa);
    }
    
    public String traduzParametro(String parametro) {
        String parametroHexa = parametro;        
        //AX 19 (decimal)
        //BX 21 (decimal)
        //CX 23 (decimal)
        //DX 25 (decimal)
        
        if (parametro.startsWith("[")) {
            parametroHexa = parametro.substring(1, parametro.length() - 1);
        }
        
        switch (parametroHexa) {
            case "AX":
                parametroHexa = "13";
                break;
            case "BX":
                parametroHexa = "15";
                break;
            case "CX":
                parametroHexa = "17";
                break;
            case "DX":
                parametroHexa = "19";
                break;
        }
        
        return parametroHexa;
    }
    
    public String mapeiaInstrucao(String[] comando) {
        
        switch (comando[0]){
            case "MOV":
                if(comando[1].contains("[")){
                    if(comando[1].contains("X")){
                    // end de registrador, registrador
                    return "5";
                    }
                    else {
                    // end de memoria, constante
                    return "6";   
                    }
                }
                else {
                    if(comando[2].contains("[")){
                        if(comando[2].contains("X")){
                            // registrador, end de registrador
                            return "4";
                        }
                        else {
                            // registrador, end de memoria 
                            return "3";
                        }
                    }
                    else if(comando[2].contains("X")){
                        // registrador, registrador
                        return "2";
                    }
                    else {
                        //registrador, constante
                        return "1";
                    } 
                }
                
            case "ADD":
                if(comando[2].contains("X")){
                    // registrador, registrador
                    return "7";
                }
                else {
                    // registrador, constante
                    return "8";
                }
                
            case "SUB":
                if(comando[2].contains("X")){
                    // registrador, registrador
                    return "9";
                }
                else {
                    // registrador, constante
                    return "A";
                }
                
            case "MUL":
                return "B";
                
            case "DIV":
                return "C";
                
            case "INC":
                return "D";
                
            case "CMP":
                if(comando[2].contains("X")){
                    // registrador, registrador
                    return "E";
                }
                else {
                    // registrador, constante
                    return "F";
                }
                
            case "JE":
                return "10";
                
            case "JNE":
                return "11";
                
            case "JG":
                return "12";
            
            case "JGE":
                return "13";
            
            case "JL":
                return "14";
            
            case "JLE":
                return "15";
        }
       return null;
    }
    
    public static void main(String[] args) {
        Interpretador compilador = new Interpretador();
        
        String[][] compilado = compilador.compilaHexa("MOV [15],5\nMOV CX,[15]");
        compilador.compilaBin(compilado);
        
        System.out.println("\nCompilado Hexa: ");
        for (String[] linha : compilado) {
            for (String s : linha) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
        
        int[][] compiladoBin = compilador.compilaBin(compilado);
        
        System.out.println("\nCompilado Bin: ");
        for (int[] linha : compiladoBin) {
            for (int s : linha) {
                System.out.print(s);
            }
            System.out.println();
        }
    }
}
