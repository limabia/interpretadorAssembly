package computador.firmware;

import computador.componentes.PalavraControle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * Data: 11/06/2018
 */
public class Firmware {
    
    private static final char DEMARCADOR_COMENTARIO = '#';
    private static final char DEMARCADOR_CODIGO_OPERACAO = '$';
    
    public static final int TAMANHO_PALAVRA_CONTROLE = 40;
    
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
    private ArrayList<PalavraControle> firmware;
    private HashMap<Integer, Integer> enderecoIntrucao;
    
    /**
     * Cria um microprograma lendo um arquivo com o microprograma.
     * 
     * @param nomeArquivoFirmware Nome do arquivo com o microprograma
     * @throws FirmwareException Falha ao ler o arquivo
     */
    public Firmware(String nomeArquivoFirmware) throws FirmwareException {
        this.firmware = new ArrayList();
        this.enderecoIntrucao = new HashMap();
        
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
        //Scanner arquivoFirmware = new Scanner(nomeArquivoFirmware);
        Scanner auxiliar;
        String palavraControleString = "0";
        int indiceLinha = 0;
        
        // Salva o microprograma em uma matriz
        while(arquivoFirmware.hasNextLine()) {
            palavraControleString = arquivoFirmware.nextLine();
            
            // Pula linha vazia ou de comentario
            if(palavraControleString.length() == 0 || 
                    palavraControleString.charAt(0) == DEMARCADOR_COMENTARIO)
                continue;
            
            // Mapeia o indice da primeira palavra de controle de cada instrucao 
            if(palavraControleString.charAt(0) == DEMARCADOR_CODIGO_OPERACAO) {
                auxiliar = new Scanner(palavraControleString.replaceAll("\\D", ""));
                this.enderecoIntrucao.put(auxiliar.nextInt(), indiceLinha);
                continue;
            }
            indiceLinha++;
            
            auxiliar = new Scanner(palavraControleString);
            
            PalavraControle palavraControle = new PalavraControle(NUMERO_SINAIS_CONTROLE);
            
            palavraControle.jumpEntradaP1(this.valorBooleano(auxiliar.nextInt()));
            palavraControle.jumpSaidaP1(this.valorBooleano(auxiliar.nextInt()));
            palavraControle.jumpSaidaP2(this.valorBooleano(auxiliar.nextInt()));
            
            for(int i = 0; i < NUMERO_SINAIS_CONTROLE; i++)
                 palavraControle.sinalDeControle(i, this.valorBooleano(auxiliar.nextInt()));
            
            palavraControle.operacaoULA(auxiliar.nextInt());
            palavraControle.operacaoRAM(auxiliar.nextInt());
            
            palavraControle.jumpIncondicional(this.valorBooleano(auxiliar.nextInt()));
            palavraControle.jumpZero(this.valorBooleano(auxiliar.nextInt()));
            palavraControle.jumpNegativo(this.valorBooleano(auxiliar.nextInt()));
            palavraControle.jumpPositivo(this.valorBooleano(auxiliar.nextInt()));
            
            palavraControle.lerIR(this.valorBooleano(auxiliar.nextInt()));
            
            palavraControle.enderecoJump(auxiliar.nextInt());
            
            this.firmware.add(palavraControle);
        }
        
        this.numeroPalavras = this.firmware.size();
    }
    
    public PalavraControle ler(int endereco) {
        return this.firmware.get(endereco);
    }
    
    public int enderecoInstrucao(int opcode) {
        return this.enderecoIntrucao.get(opcode);
    }
}
