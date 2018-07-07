package computador.layout;

import computador.Interpretador;
import computador.Computador;
import static computador.Computador.IMPRIMIRTEMPORARIO;
import computador.componentes.StatusCPU;

/**
 *
 * @author bianca
 */
public class CatWoman extends javax.swing.JFrame {
    
    Computador computador = null;


    public CatWoman() {
        initComponents();
        valorAX.setText("0");
        valorBX.setText("0");
        valorCX.setText("0");
        valorDX.setText("0");
        valorPC.setText("0");
        valorIR.setText("0");
        valorMAR.setText("0");
        valorMBR.setText("0");
        valorAC.setText("0");
        valorT.setText("0");
        palavraControle.setText("nao iniciada");
        proximaPalavra.setEnabled(false);
        pararExecucao.setEnabled(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoInstrucoes = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        palavraControle = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        iniciarExecucao = new javax.swing.JButton();
        valorIR = new javax.swing.JLabel();
        valorPC = new javax.swing.JLabel();
        valorMAR = new javax.swing.JLabel();
        valorMBR = new javax.swing.JLabel();
        valorAX = new javax.swing.JLabel();
        valorBX = new javax.swing.JLabel();
        valorCX = new javax.swing.JLabel();
        valorDX = new javax.swing.JLabel();
        proximaPalavra = new javax.swing.JButton();
        pararExecucao = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        valorAC = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        valorT = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CatWoman");

        campoInstrucoes.setColumns(20);
        campoInstrucoes.setRows(5);
        jScrollPane1.setViewportView(campoInstrucoes);

        jLabel2.setText("Digite as instruções:");

        jLabel3.setText("Palavra de controle executada:");

        palavraControle.setText("0 1 0 0 0 0 0 0 0 1 1 1 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 1 0 0 1 1");

        jLabel5.setText("IR  =");

        jLabel6.setText("PC =");

        jLabel7.setText("MAR =");

        jLabel8.setText("MBR =");

        jLabel9.setText("AX :");

        jLabel10.setText("BX :");

        jLabel11.setText("CX :");

        jLabel12.setText("DX :");

        iniciarExecucao.setText("Iniciar execução");
        iniciarExecucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarExecucaoActionPerformed(evt);
            }
        });

        valorIR.setText("1000 | 1540 | 157");

        valorPC.setText("1500");

        valorMAR.setText("1500");

        valorMBR.setText("481");

        valorAX.setText("5");

        valorBX.setText("10");

        valorCX.setText("15");

        valorDX.setText("18");

        proximaPalavra.setText("Ir para a próxima palavra de controle");
        proximaPalavra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proximaPalavraActionPerformed(evt);
            }
        });

        pararExecucao.setText("Parar execução");
        pararExecucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pararExecucaoActionPerformed(evt);
            }
        });

        jLabel4.setText("Registradores:");

        jLabel13.setText("ULA :");

        valorAC.setText("22");

        jLabel14.setText("AC =");

        jLabel15.setText("T =");

        valorT.setText("22");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(proximaPalavra, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pararExecucao, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel7))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(valorCX)
                                                    .addComponent(valorAX))
                                                .addGap(155, 155, 155)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(1, 1, 1)
                                                        .addComponent(jLabel12)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(valorDX))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel10)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(valorBX)))
                                                .addGap(33, 33, 33))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(valorMAR)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel15))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(valorPC)
                                                    .addComponent(valorMBR)
                                                    .addComponent(valorT)))))
                                    .addComponent(palavraControle, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(iniciarExecucao, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel13)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addGap(18, 18, 18)
                                            .addComponent(valorAC))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(34, 34, 34)
                                            .addComponent(valorIR)))))
                            .addComponent(jLabel2)))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(iniciarExecucao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pararExecucao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(proximaPalavra)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(palavraControle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(valorIR)
                                    .addComponent(jLabel6)
                                    .addComponent(valorPC)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valorMBR)
                            .addComponent(jLabel8)
                            .addComponent(valorMAR)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valorAC)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(valorT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(valorBX)
                            .addComponent(jLabel9)
                            .addComponent(valorAX))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(valorCX)
                            .addComponent(jLabel12)
                            .addComponent(valorDX))))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarExecucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarExecucaoActionPerformed
       campoInstrucoes.setEnabled(false);
       String codigo = campoInstrucoes.getText();
       proximaPalavra.setEnabled(true);
       
       Interpretador compilador = new Interpretador();
       int[][] codigoBin = compilador.compila(codigo);
        
        try {
            computador = new Computador(16);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao iniciar o computador: " + e.getMessage());
            System.exit(1);
        }
        
        computador.escreverPrograma(codigoBin);
        computador.executar();
        StatusCPU status = computador.getStatusCPU();
        valorAX.setText(status.getValorAX());
        valorBX.setText(status.getValorBX());
        valorCX.setText(status.getValorCX());
        valorDX.setText(status.getValorDX());
        valorPC.setText(status.getValorPC());
        valorIR.setText(status.getValorIR());
        valorMAR.setText(status.getValorMAR());
        valorMBR.setText(status.getValorMBR());
        valorT.setText(status.getValorT());
        valorAC.setText(status.getValorAC());
        
        //IMPRIMIRTEMPORARIO(status);
        proximaPalavra.setEnabled(true);
        pararExecucao.setEnabled(true);
        iniciarExecucao.setEnabled(true);
    }//GEN-LAST:event_iniciarExecucaoActionPerformed

    private void proximaPalavraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proximaPalavraActionPerformed
        try {
            computador.executar();
            StatusCPU status = computador.getStatusCPU();
            IMPRIMIRTEMPORARIO(status);        
        } catch(Exception e) {
            System.out.println("\nDeu erro! Hora de parar!\n");
        }
    }//GEN-LAST:event_proximaPalavraActionPerformed

    private void pararExecucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pararExecucaoActionPerformed
        computador = null;
        proximaPalavra.setEnabled(false);
        campoInstrucoes.setEnabled(true);
        iniciarExecucao.setEnabled(true);
    }//GEN-LAST:event_pararExecucaoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CatWoman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CatWoman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CatWoman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CatWoman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CatWoman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextArea campoInstrucoes;
    private javax.swing.JButton iniciarExecucao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel palavraControle;
    private javax.swing.JButton pararExecucao;
    private javax.swing.JButton proximaPalavra;
    private javax.swing.JLabel valorAC;
    private javax.swing.JLabel valorAX;
    private javax.swing.JLabel valorBX;
    private javax.swing.JLabel valorCX;
    private javax.swing.JLabel valorDX;
    private javax.swing.JLabel valorIR;
    private javax.swing.JLabel valorMAR;
    private javax.swing.JLabel valorMBR;
    private javax.swing.JLabel valorPC;
    private javax.swing.JLabel valorT;
    // End of variables declaration//GEN-END:variables
}
