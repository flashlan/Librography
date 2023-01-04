/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

import br.com.projeto.jdbc.ConexaoBanco;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author orfeu
 */
public class XlsxProcess {

    private Connection conexao;

    //construtor
    public XlsxProcess() {
        this.conexao = new ConexaoBanco().pegarConexao();
    }

    public void importaUsuariosXls() throws FileNotFoundException, IOException, SQLException {
        
        // java.sql.PreparedStatement stmt = null;
        final JFileChooser fc = new JFileChooser();
        File file = fc.getSelectedFile(); //if xls only
        java.sql.PreparedStatement stmt = null;
        FileInputStream input = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(input);
        Workbook workbook;
        workbook = WorkbookFactory.create(fs);
        Sheet sheet = workbook.getSheetAt(0);
        Row row;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = (Row) sheet.getRow(i);
            String nome = row.getCell(0).getStringCellValue();
            String rg = row.getCell(1).getStringCellValue();
            String cpf = row.getCell(2).getStringCellValue();
            String  email= row.getCell(3).getStringCellValue();
            String  telefone= row.getCell(4).getStringCellValue();
            String  celular= row.getCell(5).getStringCellValue();
            String  cep= row.getCell(6).getStringCellValue();
            String  endereco= row.getCell(7).getStringCellValue();
            String  numero= row.getCell(8).getStringCellValue();
            String complemento = row.getCell(9).getStringCellValue();
            String  bairro= row.getCell(10).getStringCellValue();
            String  cidade= row.getCell(11).getStringCellValue();
            String  estado= row.getCell(12).getStringCellValue();
            String  curso= row.getCell(13).getStringCellValue();
            String  curso_ano= row.getCell(14).getStringCellValue();
            String  qtd_emprestimos= "0";
            String  emprestmax= row.getCell(15).getStringCellValue();
            String  observacoes= row.getCell(16).getStringCellValue();
            String  tipo= row.getCell(17).getStringCellValue();
            String  is_locked= "0";
            //String  = row.getCell(20).getStringCellValue();
            
            String sql = "insert into tb_leitores (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado, curso, curso_ano, qtd_emprestimos, emprestmax, observacoes, tipo, is_locked )"
                    + "values("+nome +", "+ rg+", "+ cpf+","+email +","+ telefone+", "
                    + ""+ celular+", "+cep +", "+ endereco+", "+numero +", "+ complemento+", "+ bairro+","
                    + " "+ cidade+", "+estado +", "+ curso+", "+ curso_ano+", "+ qtd_emprestimos+", "+ emprestmax+","
                    + " "+observacoes +", "+ tipo+", "+ is_locked+")";

            stmt = (PreparedStatement) conexao.prepareStatement(sql);
            stmt.execute();
            System.out.println("Import rows " + i);
        
        }
        conexao.commit();
        stmt.close();
        conexao.close();
        input.close();
        System.out.println("Success import excel to mysql table");
    }

}
