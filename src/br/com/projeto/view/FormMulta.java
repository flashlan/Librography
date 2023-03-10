/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.view;

import br.com.projeto.dao.EmprestimoDao;
import br.com.projeto.dao.FuncionarioDao;
import br.com.projeto.dao.LivroDao;
import br.com.projeto.dao.MultaDao;
import br.com.projeto.dao.ReciboDao;
import br.com.projeto.model.Funcionario;
import br.com.projeto.model.Multa;
import br.com.projeto.model.Utilitarios;
import java.awt.Font;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class FormMulta extends javax.swing.JFrame {

    /**
     * Creates new form FormMulta
     */
    public FormMulta() {
        initComponents();
    }

    public void listarMulta(int idDoEmprestimo) throws Exception {
        MultaDao dao = new MultaDao();
        EmprestimoDao emprest = new EmprestimoDao();
        Utilitarios util = new Utilitarios();
        LivroDao lvr = new LivroDao();
        List<Multa> lista = dao.listaMulta(idDoEmprestimo);
        for (Multa c : lista) {
            txtNumMulta.setText(String.valueOf(c.getId()));
            txtDiasAtraso.setText(String.valueOf(c.getDias_atraso()));
            txtValorMulta.setText(util.campoMulta(c.getValor_multa()));//(util.campoMulta(multa))
            Boolean esta_pago = c.isEsta_pago();
            txtLeitorId.setText(emprest.getUserData("nome", c.getTb_leitores_id()));//formatter
            txtEmprestimoId.setText(String.valueOf(c.getTb_emprestimos_id()));
            txtNomeLivro.setText(lvr.getLivroData("titulo", emprest.getEmprestimoFKeyData("tb_livros_id", c.getTb_emprestimos_id())));//formatter
        }
    }

    public FormMulta(int msgEmpId) throws Exception { //cria construtor apenas com a variavel, que ?? usada para consulta no db e popular campos
        initComponents();
        listarMulta(msgEmpId);
        //esta criando uma multa a cada vez que clica no botao

    }

    public void imprimeMulta(int idDoEmprestimo) throws Exception {
        Utilitarios util = new Utilitarios();
        Funcionario funcionario = new Funcionario();
        String contentid = new String(Files.readAllBytes(Paths.get("C:\\Librography\\LoggedIn")));
        funcionario.setId(Integer.parseInt(contentid));
        FuncionarioDao funcionariodao = new FuncionarioDao();
        String nomeFuncionario = funcionariodao.getFuncionarioData("nome", funcionario.getId());
        String toBCode = String.format("%08d", idDoEmprestimo);
        //int code = Integer.parseInt(toBCode);
        util.gerarBarCode("Multa", idDoEmprestimo);
        util.gerarQrCode("Multa", idDoEmprestimo);
        
        //System.out.println("code==" + code);
        
        String QrImage = "file:C:\\\\Librography\\\\images\\\\Multas\\\\QrCode\\\\" + toBCode;
        String BarCodeImage = "file:C:\\\\Librography\\\\images\\\\Multas\\\\BarCode\\\\" + toBCode;
        String filepath = "C:\\Librography\\ticket";
        //LivroDao livro = new LivroDao();
        String livroNome = txtNomeLivro.getText();
        File arquivo = new File(filepath);
        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }
        String line = "Obrigado pela Preferencia";
        JEditorPane p = new JEditorPane("file:" + filepath);
        p.setContentType("text/html");
        p.setFont(new Font("Helvetica", 0, 9));
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><head></head><body><p>");
        htmlContent.append("<h3><img src='file:C:\\Librography\\images\\libraryLogo.png'  width=30 height=30></img>");
        htmlContent.append("BIBLIOTECA DE HOGWARTS</h3>");
        htmlContent.append("<h3 align=center>MULTA POR ATRASO</h3><br>");
        htmlContent.append("LIVRO:");
        htmlContent.append("<h4 align=right>").append(String.format("%26s", livroNome)).append("</h4>");
        htmlContent.append("Valor da Multa:");
        htmlContent.append("<h4 align=right>").append(String.format("%26s", txtValorMulta.getText())).append("</h4>");
        htmlContent.append("Referenmte Empr??stimo n??::");
        htmlContent.append("<h4 align=right>").append(String.format("%26s", txtEmprestimoId.getText())).append("</h4>");
        htmlContent.append(" Multa: ").append(String.format("%26s", txtValorMulta.getText())).append("<br>");
        htmlContent.append("Dias de Atraso:: ").append(String.format("%26s", txtDiasAtraso.getText())).append("<br>");
        htmlContent.append(" Usu??rio: ").append(String.format("%26s", txtLeitorId.getText().toUpperCase())).append("<br>");
        htmlContent.append(" Atendente: ").append(String.format("%26s", nomeFuncionario.toUpperCase())).append("<br>");
        htmlContent.append("<img src='").append(BarCodeImage).append("'  width=100 height=40></img>");
        htmlContent.append("<img src='").append(QrImage).append("'  width=40 height=40></img><br>");
        htmlContent.append("<font face=\"monospace\">").append(line).append("</font><br><br><br>");
        htmlContent.append("</body>");
        htmlContent.append("</html>");
        p.setText(htmlContent.toString());
        ReciboDao recibodao = new ReciboDao();
        recibodao.imprimirTicket(p, 1);
        arquivo.delete();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtImprimeMulta = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtNumMulta = new javax.swing.JTextField();
        txtEmprestimoId = new javax.swing.JTextField();
        txtLeitorId = new javax.swing.JTextField();
        txtDiasAtraso = new javax.swing.JTextField();
        txtNomeLivro = new javax.swing.JTextField();
        txtValorMulta = new javax.swing.JTextField();
        btnReceberMulta = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFocusable(false);
        setFocusableWindowState(false);
        setMinimumSize(new java.awt.Dimension(448, 175));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(448, 175));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel2.setText("N?? da Multa:");

        jLabel1.setText("Livro:");

        jLabel4.setText("Dias de Atraso:");

        jLabel5.setText("Valor da Multa:");

        jLabel6.setText("Id de Usu??rio:");

        jLabel7.setText("Referente Empr??stimo n??:");

        txtImprimeMulta.setText("Imprimir");
        txtImprimeMulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImprimeMultaActionPerformed(evt);
            }
        });

        jButton2.setText("Fechar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnReceberMulta.setText("Receber");
        btnReceberMulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceberMultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLeitorId))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmprestimoId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtDiasAtraso, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeLivro))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtValorMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtImprimeMulta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReceberMulta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumMulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLeitorId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDiasAtraso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmprestimoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtValorMulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtImprimeMulta)
                        .addComponent(jButton2)
                        .addComponent(btnReceberMulta)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 22, 440, 150);

        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setOpaque(false);

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("MULTA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 520, 30);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        super.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        //pegar dados de user
    }//GEN-LAST:event_formWindowActivated

    private void btnReceberMultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceberMultaActionPerformed
        String multa = txtValorMulta.getText();
        if (!multa.equals("Em dia")) {

            Utilitarios util = new Utilitarios();
            int i = util.okcancel("Confirma o recebimento do valor de " + multa + ", Recebimento do Livro e desbloqueio do usu??rio?");
            System.out.println("ret : " + i);
            try {
                EmprestimoDao devEmpres = new EmprestimoDao();
                MultaDao multaDao = new MultaDao();
                devEmpres.devolveLivro(Integer.parseInt(txtEmprestimoId.getText()));
                multaDao.zeraMulta(Integer.parseInt(txtNumMulta.getText()));
            } catch (Exception ex) {
                Logger.getLogger(FormEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "N??o h?? multa registrada");
        }
    }//GEN-LAST:event_btnReceberMultaActionPerformed

    private void txtImprimeMultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImprimeMultaActionPerformed
        try {
            this.imprimeMulta(Integer.parseInt(txtEmprestimoId.getText()));
        } catch (Exception ex) {
            Logger.getLogger(FormMulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtImprimeMultaActionPerformed

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
            java.util.logging.Logger.getLogger(FormMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReceberMulta;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtDiasAtraso;
    private javax.swing.JTextField txtEmprestimoId;
    private javax.swing.JButton txtImprimeMulta;
    private javax.swing.JTextField txtLeitorId;
    private javax.swing.JTextField txtNomeLivro;
    private javax.swing.JTextField txtNumMulta;
    private javax.swing.JTextField txtValorMulta;
    // End of variables declaration//GEN-END:variables
}
