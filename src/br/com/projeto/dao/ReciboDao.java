/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Emprestimo;
import br.com.projeto.model.Recibo;
import br.com.projeto.model.Utilitarios;
import com.mysql.jdbc.Connection;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.MediaTray;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class ReciboDao {

    private File ticket;

    //contstrutor
    public ReciboDao(File f) {
        ticket = f;
    }

    private Connection conexao;

    //conexao
    public ReciboDao(Connection conexao) {
        this.conexao = new ConexaoBanco().pegarConexao();
    }

    //construtor
    public ReciboDao() {
        this.conexao = new ConexaoBanco().pegarConexao();
    }

    //metodo cadastrar Funcionario
    public void cadastrarReciboEmprestimo(Recibo obj) {
        try {
            String sql = "insert into tb_recibos (emprestimo_id, data_emprestimo, data_devolucao_agendada, livro, usuario, tipo, status, funcionario)"
                    + "values(?,?,?,?,?,?,?,?)";
            java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getEmprestimoid());
            stmt.setString(2, obj.getData_emprestimo());
            stmt.setString(3, obj.getData_devolução_agendada());
            stmt.setString(4, obj.getLivro());
            stmt.setString(5, obj.getUsuario());
            stmt.setString(6, obj.getTipo());
            stmt.setString(7, obj.getStatus());
            stmt.setString(8, obj.getFuncionario());
            stmt.execute();
            stmt.close();
            this.imprimeCupom58Emprestimo(obj);
            String filepath = "C:\\Librography\\ticket.txt";
            PrintWriter pw = new PrintWriter(filepath);
            pw.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro!" + erro);
        }
    }

    public void imprimeCupom58Emprestimo(Recibo obj) throws IOException, PrinterException, PrintException {
        int msg = obj.getEmprestimoid();
        //String Code = String.format("%08d", msg);
        Utilitarios util = new Utilitarios();
        util.gerarBarCode("emprestimo", msg);
        util.gerarQrCode("emprestimo", msg);
        String toCode = String.format("%08d", msg);
        String QrImage = "file:C:\\\\Librography\\\\images\\\\Emprestimos\\\\QrCode\\\\" + toCode;
        String BarCodeImage = "file:C:\\\\Librography\\\\images\\\\Emprestimos\\\\BarCode\\\\" + toCode;
        String filepath = "C:\\Librography\\ticket";
        File arquivo = new File(filepath);
        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }
        String line = "Obrigado pela Preferencia"; /// options get message
        JEditorPane p = new JEditorPane("file:" + filepath);
        p.setContentType("text/html");
        p.setFont(new Font("Helvetica", 0, 9));
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><head></head><body><p>");
        htmlContent.append("<h3><img src='file:C:\\Librography\\images\\libraryLogo.png'  width=30 height=30></img>");
        htmlContent.append("BIBLIOTECA DE HOGWARTS</h3>");
        htmlContent.append("<h3 align=center>RECIBO DE EMPRÉSTIMO</h3><br>");
        htmlContent.append("LIVRO:");
        htmlContent.append("<h4 align=right>").append(String.format("%26s", obj.getLivro())).append("</h4>");
        htmlContent.append(" Data Empréstimo:");
        htmlContent.append("<h4 align=right>").append(String.format("%26s", obj.getData_emprestimo().toUpperCase())).append("</h4>");
        htmlContent.append(" Data Devolução:");
        htmlContent.append("<h4 align=right>").append(String.format("%26s", obj.getData_devolução_agendada().toUpperCase())).append("</h4>");
        htmlContent.append(" Usuário: ").append(String.format("%26s", obj.getUsuario().toUpperCase())).append("<br>");
        htmlContent.append(" Atendente: ").append(String.format("%26s", obj.getFuncionario().toUpperCase())).append("<br>");
        htmlContent.append("<img src='").append(BarCodeImage).append("'  width=100 height=40></img>");
        htmlContent.append("<img src='").append(QrImage).append("'  width=40 height=40></img><br>");
        htmlContent.append("<font face=\"monospace\">").append(line).append("</font><br><br><br>");
        htmlContent.append("</body>");
        htmlContent.append("</html>");
        p.setText(htmlContent.toString());
        this.imprimirTicket(p, 1);
        arquivo.delete();
    }

    public void imprimirTicket(JEditorPane resultadoTicket, int numeroImpressoes) {
        try {
            for (int i = 0; i < numeroImpressoes; i++) {
                PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
                attributes.add(new Copies(1));
                attributes.add(MediaTray.TOP);
                attributes.add(MediaSizeName.INVOICE);
                attributes.add(new MediaPrintableArea(0f, 0f, 58f, 210f, MediaPrintableArea.MM));
                PrintService service = PrintServiceLookup.lookupDefaultPrintService();
                System.out.println("impressora" + service.getName());
                if (!service.getName().equals("Dialogo")) {
                    resultadoTicket.print(null, null, true, null, attributes, true);
                } else {
                    resultadoTicket.print(null, null, false, service, attributes, false);
                }
            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    public void imprimirDevolucao58(Emprestimo obj) throws IOException, Exception {
        int msg = obj.getId();
        Utilitarios util = new Utilitarios();
        util.gerarBarCode("devolucao", msg);
        util.gerarQrCode("devolucao", msg);
        String toCode = String.format("%08d", msg);
        String QrImage = "file:C:\\\\Librography\\\\images\\\\Devolucao\\\\QrCode\\\\" + toCode;
        String BarCodeImage = "file:C:\\\\Librography\\\\images\\\\Devolucao\\\\BarCode\\\\" + toCode;
        String filepath = "C:\\Librography\\ticket";
        File arquivo = new File(filepath);
        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }
        String line = "Obrigado pela Preferencia"; /// options get message
        JEditorPane p = new JEditorPane("file:" + filepath);
        p.setContentType("text/html");
        p.setFont(new Font("Helvetica", 0, 9));
        UsuarioDao usuariodao = new UsuarioDao();
        LivroDao livrodao = new LivroDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        String usuarioNome = usuariodao.getUserData("nome", obj.getTb_leitores_id().getId());
        String funcionarioNome = funcionarioDao.getFuncionarioData("nome", obj.getTb_funcionarios_id().getId());
        String livroNome = livrodao.getLivroData("titulo", obj.getTb_livros_id().getId());
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><head></head><body><p>");
        htmlContent.append("<h3><img src='file:C:\\Librography\\images\\libraryLogo.png'  width=30 height=30></img>");
        OptionsDao optionsdao = new OptionsDao();
        String LivryName = optionsdao.retornaOption(15);
        
        htmlContent.append(LivryName).append("</h3>");
        htmlContent.append("<h3 align=center>RECIBO DE DEVOLUÇÃO</h3><br>");
        htmlContent.append("LIVRO:");
        htmlContent.append("<h4 align=right>").append(String.format("%26s", livroNome)).append("</h4>");
        htmlContent.append(" Data Empréstimo:");
        htmlContent.append("<h4 align=right>").append(String.format("%26s", util.formatData(obj.getData_emprestimo()))).append("</h4>");
        htmlContent.append(" Data Devolução:");
        htmlContent.append("<h4 align=right>").append(String.format("%26s", util.formatData(obj.getData_devolucao()))).append("</h4>");
        htmlContent.append(" Usuário: ").append(String.format("%26s", usuarioNome.toUpperCase())).append("<br>");
        htmlContent.append(" Atendente: ").append(String.format("%26s", funcionarioNome.toUpperCase())).append("<br>");
        htmlContent.append("<img src='").append(BarCodeImage).append("'  width=100 height=40></img>");
        htmlContent.append("<img src='").append(QrImage).append("'  width=40 height=40></img><br>");
        htmlContent.append("<font face=\"monospace\">").append(line).append("</font><br><br><br>");
        htmlContent.append("</body>");
        htmlContent.append("</html>");
        p.setText(htmlContent.toString());
        this.imprimirTicket(p, 1);
        arquivo.delete();

    }

}
