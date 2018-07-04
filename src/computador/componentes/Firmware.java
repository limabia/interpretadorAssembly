package computador.componentes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * Data: 11/06/2018
 */
public class Firmware {
    
    private static final char DEMARCADOR_COMENTARIO = '#';
    
    public static final int TAMANHO_PALAVRA_CONTROLE = 39;
    
    /******************************** INDICES ********************************/
    
    public static final int INDICE_FLAG_ENTRADA_P1 = 0;
    public static final int INDICE_FLAG_SAIDA_P1 = 1;
    public static final int INDICE_FLAG_SAIDA_P2 = 2;
    
    public static final int INDICE_SINAIS_CONTROLE = 3;
    public static final int NUMERO_SINAIS_CONTROLE = 30;
    
    public static final int INDICE_SINAIS_ULA = 33;
    
    public static final int INDICE_SINAIS_MEMORIA = 34;
    
    public static final int INDICE_JUMP_INCONDICIONAL = 35;
    public static final int INDICE_JUMP_ZERO = 36;
    public static final int INDICE_JUMP_OVERFLOW = 37;
    
    public static final int INDICE_FLAG_LER_IR = 38;
    public static final int INDICE_ENDERECO_JUMP = 39;
    
    
    private int numeroPalavras;
    private PalavraControle[] firmware;
    
    /**
     * Cria um microprograma lendo um arquivo com o microprograma.
     * 
     * @param nomeArquivoFirmware Nome do arquivo com o microprograma
     * @throws FirmwareException Falha ao ler o arquivo
     */
    public Firmware(String nomeArquivoFirmware) throws FirmwareException {
        try {
            carregar(nomeArquivoFirmware);
        } catch(InputMismatchException ime) {
            throw new FirmwareException("O microprograma esta escrito em um "
                    + "formato invalido: " + ime.getMessage());
        } catch(FileNotFoundException fnfe) {
            throw new FirmwareException("Nao foi possivel carregar o micro"
                    + "programa: " + fnfe.getMessage());
        }
    }
    
    private boolean valorBooleano(int valor) {
        return (valor == 1);
    }
    
    /**
     * Transforma um arquivo com um microprograma em um microprograma armazenado
     * em um vetor de inteiros
     * 
     * @param nomeArquivoFirmware Nome do arquivo com o microprograma
     * @throws FileNotFoundException Arquivo nao encontrado
     */
    private void carregar(String nomeArquivoFirmware) throws FileNotFoundException{
        Scanner arquivoFirmware = new Scanner(new File(nomeArquivoFirmware));
        
        Scanner auxiliar;
        String palavraControle = "0";
        int indiceLinha = 0;
        
        // Procura a linha com o numero de palavras de controle do microprograma
        while(arquivoFirmware.hasNextLine()) {
            palavraControle = arquivoFirmware.nextLine();
            
            // Caso a linha nao esteja vazia e nao seja de comentario para a busca
            if(palavraControle.length() == 0 && 
                    palavraControle.charAt(0) != DEMARCADOR_COMENTARIO)
                break;
        }
        
        numeroPalavras = new Scanner(palavraControle).nextInt();
        
        this.firmware = new PalavraControle[numeroPalavras];
        
        // Salva o microprograma em uma matriz
        while(arquivoFirmware.hasNextLine()) {
            palavraControle = arquivoFirmware.nextLine();
            
            // Pula linha vazia ou de comentario
            if(palavraControle.length() == 0 || 
                    palavraControle.charAt(0) == DEMARCADOR_COMENTARIO)
                continue;
            
            this.firmware[indiceLinha] = new PalavraControle();
            
            auxiliar = new Scanner(palavraControle);
            
            this.firmware[indiceLinha].jumpEntradaP1(this.valorBooleano(auxiliar.nextInt()));
            this.firmware[indiceLinha].jumpSaidaP1(this.valorBooleano(auxiliar.nextInt()));
            this.firmware[indiceLinha].jumpSaidaP2(this.valorBooleano(auxiliar.nextInt()));
            //this.firmware[indiceLinha][INDICE_FLAG_ENTRADA_P1] = auxiliar.nextInt();
            //this.firmware[indiceLinha][INDICE_FLAG_SAIDA_P1] = auxiliar.nextInt();
            //this.firmware[indiceLinha][INDICE_FLAG_SAIDA_P2] = auxiliar.nextInt();
            
            boolean[] sinaisDeControle = new boolean[NUMERO_SINAIS_CONTROLE];
            
            for(int i = 0; i < sinaisDeControle.length; i++)
                sinaisDeControle[i] = this.valorBooleano(auxiliar.nextInt());
            this.firmware[indiceLinha].sinaisDeControle(sinaisDeControle);
            //for(int i = 0; i < NUMERO_SINAIS_CONTROLE; i++)
               // this.firmware[indiceLinha][INDICE_SINAIS_CONTROLE + i] = auxiliar.nextInt();
            
            this.firmware[indiceLinha].operacaoULA(auxiliar.nextInt());
            this.firmware[indiceLinha].operacaoRAM(auxiliar.nextInt());
            //this.firmware[indiceLinha][INDICE_SINAIS_ULA] = auxiliar.nextInt();
            //this.firmware[indiceLinha][INDICE_SINAIS_MEMORIA] = auxiliar.nextInt();
            
            this.firmware[indiceLinha].jumpIncondicional(this.valorBooleano(auxiliar.nextInt()));
            this.firmware[indiceLinha].jumpZero(this.valorBooleano(auxiliar.nextInt()));
            this.firmware[indiceLinha].jumpOverflow(this.valorBooleano(auxiliar.nextInt()));
            
            //this.firmware[indiceLinha][INDICE_JUMP_INCONDICIONAL] = auxiliar.nextInt();
            //this.firmware[indiceLinha][INDICE_JUMP_ZERO] = auxiliar.nextInt();
            //this.firmware[indiceLinha][INDICE_JUMP_OVERFLOW] = auxiliar.nextInt();
            
            this.firmware[indiceLinha].lerIR(this.valorBooleano(auxiliar.nextInt()));
            
            this.firmware[indiceLinha].enderecoJump(auxiliar.nextInt());
            //this.firmware[indiceLinha][INDICE_ENDERECO_JUMP] = auxiliar.nextInt();
            
            indiceLinha++;
        }
    }
    
    public PalavraControle ler(int endereco) {
        return this.firmware[endereco];
    }
}
