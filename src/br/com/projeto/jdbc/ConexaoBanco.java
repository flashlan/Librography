/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;

import br.com.projeto.model.Utilitarios;
import br.com.projeto.view.FormOptions;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class ConexaoBanco {

    Utilitarios util = new Utilitarios();

    public Connection pegarConexao() {
        try {

            String contentIP = null;
            String ipserverPath = "";
            String dbuserpPath = "";
            String dbpassPath = "";
            String dbTypePath = "";

            switch (util.getOS()) {
                case WINDOWS:
                    ipserverPath = "C:\\Librography\\ipserver";
                    dbuserpPath = "C:\\Librography\\DBUser";
                    dbpassPath = "C:\\Librography\\DBPass";
                    dbTypePath = "C:\\Librography\\DBType";
                    break;
                case MAC:
                    ipserverPath = "//Applications//Librography.app//config//ipserver";
                    dbuserpPath = "//Applications//Librography.app//config//DBUser";
                    dbpassPath = "//Applications//Librography.app//config//DBPass";
                    dbTypePath = "//Applications//Librography.app//config//DBType";
                    break;
                case LINUX:
                    ipserverPath = "//opt//Librography//ipserver";
                    dbuserpPath = "//opt//Librography//DBUser";
                    dbpassPath = "//opt//Librography//DBPass";
                    dbTypePath = "//opt//Librography//DBType";
                    // some stuff
                    break;
            }
            try {
                contentIP = new String(Files.readAllBytes(Paths.get(ipserverPath)));
            } catch (IOException ex) {
                Logger.getLogger(FormOptions.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            String contentUser;
            contentUser = new String(Files.readAllBytes(Paths.get(dbuserpPath)));

            String contentPass;
            contentPass = new String(Files.readAllBytes(Paths.get(dbpassPath)));
            
            String contentDBType =  new String(Files.readAllBytes(Paths.get(dbTypePath)));
            
            String contentDBPort = "";
            if(contentDBType.contains("mysql")) {
                contentDBPort = "3306";
            } else if (contentDBType.contains("postgresql")) {
                contentDBPort = "5432";
            }

            //String url = "jdbc:mysql://" + contentIP + ":3306/applibrography"; //Nome da base de dados
            String url = "jdbc:" + contentDBType + "://" + contentIP + ":" +  contentDBPort + "/applibrography"; //Nome da base de dados
            String user = contentUser; //nome do usuário do MySQL
            String password = contentPass; //senha do MySQL
            //System.out.println(url + user + password);

            Connection conexao = null;
            conexao = (Connection) DriverManager.getConnection(url, user, password);
            

            return conexao;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro" + e);
        }
        return null;
    }

}
