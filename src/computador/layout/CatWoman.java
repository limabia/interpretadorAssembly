package computador.layout;

import computador.Interpretador;
import computador.Computador;
import static computador.Computador.IMPRIMIRTEMPORARIO;
import computador.componentes.StatusCPU;
import javax.swing.JOptionPane;

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
        proximaPalavra.setEnabled(false);
        pararExecucao.setEnabled(false);
    }

    
    private void printaTela(StatusCPU status){
        try{
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
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao preencher os valores na tela: " + e.getMessage());
        }
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

        jLabel1.setText("Bem vindo ao CatWoman!");

        campoInstrucoes.setColumns(20);
        campoInstrucoes.setRows(5);
        jScrollPane1.setViewportView(campoInstrucoes);

        jLabel2.setText("Digite as instruções:");

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
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(iniciarExecucao, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(pararExecucao, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(proximaPalavra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(34, 34, 34)
                                            .addComponent(valorIR))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addGap(18, 18, 18)
                                            .addComponent(valorMAR))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(42, 42, 42)
                                            .addComponent(valorPC))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(30, 30, 30)
                                            .addComponent(valorMBR))
                                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(valorAC))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addGap(56, 56, 56)
                                                .addComponent(valorT))))
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jLabel12)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(valorDX))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(valorBX))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addGap(35, 35, 35)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(valorCX)
                                                .addComponent(valorAX))))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iniciarExecucao)
                            .addComponent(pararExecucao))
                        .addGap(18, 18, 18)
                        .addComponent(proximaPalavra)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valorIR)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(valorMAR))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(valorPC))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(valorMBR))
                        .addGap(44, 44, 44)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valorAC)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(valorT))
                        .addGap(54, 54, 54)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(valorAX))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(valorCX))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(valorBX))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(valorDX)))
                    .addComponent(jScrollPane1))
                .addContainerGap(22, Short.MAX_VALUE))
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
            JOptionPane.showMessageDialog(null,"Erro ao iniciar o computador: " + e.getMessage());
        }
        
        computador.escreverPrograma(codigoBin);
        computador.executar();
        StatusCPU status = computador.getStatusCPU();
        printaTela(status);
        //IMPRIMIRTEMPORARIO(status);
        proximaPalavra.setEnabled(true);
        pararExecucao.setEnabled(true);
        iniciarExecucao.setEnabled(true);
    }//GEN-LAST:event_iniciarExecucaoActionPerformed

    private void proximaPalavraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proximaPalavraActionPerformed
        try {
            computador.executar();
            StatusCPU status = computador.getStatusCPU();
            //IMPRIMIRTEMPORARIO(status);    
            printaTela(status);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Final da execução");
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
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
