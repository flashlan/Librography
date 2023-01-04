/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.projeto.view.FormCartao;
import com.google.zxing.WriterException;
import java.awt.Component;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.lt;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import static org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.model;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Everton Luiz Kozloski - evertonkozloski@hotmail.com
 */
public class Utilitarios {

    // método limpar tela
    public void limpaTela(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField || component instanceof JTextArea) {
                ((JTextField) component).setText(null);
            }
            if (component instanceof JComboBox) {
                ((JComboBox) component).setSelectedIndex(-1);
            }
            if (component instanceof JLabel) {
                ((JLabel) component).setIcon(null);
            }
        }
    }

    public static boolean isNegative(double d) {
        return Double.doubleToRawLongBits(d) < 0;
    }

    public interface DateUtil {

        String ISO_DATE_FORMAT_ZERO_OFFSET = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String UTC_TIMEZONE_NAME = "UTC";

        static SimpleDateFormat provideDateFormat() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ISO_DATE_FORMAT_ZERO_OFFSET);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC_TIMEZONE_NAME));
            return simpleDateFormat;
        }
    }

    public String campoMulta(double multa) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        if (multa <= 0) {
            String e = "Em dia";
            return e;
        } else {
            String e = formatter.format(multa);
            return e;
        }
    }

    public static int okcancel(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage,
                "Atenção!", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    /**
     *
     * @param tipo
     * @param id
     */
    public void gerarBarCode(String tipo, int id) {
        String toCode = String.format("%08d", id);
        String BAR_CODE_IMAGE_PATH = "C:\\Librography\\images\\Emprestimos\\BarCode\\";
        switch (tipo) {
            case "emprestimo":
                BAR_CODE_IMAGE_PATH = "C:\\\\Librography\\\\images\\\\Emprestimos\\\\BarCode\\\\";
                break;
            case "devolucao":
                BAR_CODE_IMAGE_PATH = "C:\\\\Librography\\\\images\\\\Devolucao\\\\BarCode\\\\";
                break;
            case "usuario":
                BAR_CODE_IMAGE_PATH = "C:\\\\Librography\\\\images\\\\Usuarios\\\\BarCode\\\\";
                break;
            case "card":
                BAR_CODE_IMAGE_PATH = "C:\\\\Librography\\\\images\\\\Cards\\\\BarCode\\\\";
                break;
            case "book":
                BAR_CODE_IMAGE_PATH = "C:\\\\Librography\\\\images\\Books\\\\BarCode\\\\";
                break;
        }
        System.out.println(BAR_CODE_IMAGE_PATH);
        String Finalbpath = BAR_CODE_IMAGE_PATH + toCode;
        System.out.println(Finalbpath);
        QRCodeGenerator genBarCode = new QRCodeGenerator();
        try {
            genBarCode.generateBarCodeImage(toCode, 340, 150, Finalbpath);
        } catch (WriterException ex) {
            Logger.getLogger(FormCartao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormCartao.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String BarCodeImage = "C:\\Librography\\images\\Emprestimos\\QrCode\\" + toCode;
    }

    public void gerarQrCode(String tipo, int id) {
        String toCode = String.format("%08d", id);
        String QR_CODE_IMAGE_PATH = "C:\\Librography\\images\\Emprestimos\\QrCode\\";
        switch (tipo) {
            case "emprestimo":
                QR_CODE_IMAGE_PATH = "C:\\\\Librography\\\\images\\\\Emprestimos\\\\QrCode\\\\";
                break;
            case "devolucao":
                QR_CODE_IMAGE_PATH = "C:\\\\Librography\\\\images\\\\Devolucao\\\\QrCode\\\\";
                break;
            case "usuario":
                QR_CODE_IMAGE_PATH = "C:\\\\Librography\\\\images\\\\Usuarios\\\\QrCode\\\\";
                break;
            case "card":
                QR_CODE_IMAGE_PATH = "C:\\\\Librography\\\\images\\\\Cards\\\\QrCode\\\\";
                break;
            case "book":
                QR_CODE_IMAGE_PATH = "C:\\\\Librography\\\\images\\\\Books\\\\QrCode\\\\";
                break;
        }
        String Finalbpath = QR_CODE_IMAGE_PATH + toCode;
        QRCodeGenerator genCode = new QRCodeGenerator();

        try {
            genCode.generateQRCodeImage(toCode, 550, 550, Finalbpath);
        } catch (WriterException ex) {
            Logger.getLogger(FormCartao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormCartao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String formatData(Timestamp timestamp) {
        SimpleDateFormat dataBR = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataFormatada = dataBR.format(timestamp);
        return dataFormatada;
    }

    public boolean valida(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpf);
            return true;
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "CPF Inválido! Tente Novamente!");
//        e.printStackTrace(); 
            return false;
        }
    }

    public boolean validaCnpj(String cpf) {
        CNPJValidator cnpjValidator = new CNPJValidator();
        try {
            cnpjValidator.assertValid(cpf);
            return true;
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "CNPJ Inválido! Tente Novamente!");
//        e.printStackTrace(); 
            return false;
        }
    }

    public void toExcel(JTable table, File file) throws FileNotFoundException, IOException {
        FileOutputStream excelFos = null;
        XSSFWorkbook excelJTableExport = null;
        BufferedOutputStream excelBos = null;
        try {
            TableModel model = table.getModel();
            excelJTableExport = new XSSFWorkbook();
            XSSFSheet excelSheet = excelJTableExport.createSheet("Jtable Export");
            for (int i = 0; i < model.getRowCount(); i++) {
                XSSFRow excelRow = excelSheet.createRow(i);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    XSSFCell excelCell = excelRow.createCell(j);
                    if (model.getValueAt(i, j) == null) {
                         excelCell.setCellValue("");
                    } else {
                        String ccell = model.getValueAt(i, j).toString();
                        excelCell.setCellValue(ccell);
                    }
                }
            }
            excelFos = new FileOutputStream(file);
            excelBos = new BufferedOutputStream(excelFos);
            excelJTableExport.write(excelBos);
            //JOptionPane.showMessageDialog(null, "Exported Successfully");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (excelBos != null) {
                    excelBos.close();
                }
                if (excelFos != null) {
                    excelFos.close();
                }
                if (excelJTableExport != null) {
                    excelJTableExport.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public enum OS {
        WINDOWS, LINUX, MAC, SOLARIS
    };// Operating systems.

    private static OS os = null;

    public static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                os = OS.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                os = OS.LINUX;
            } else if (operSys.contains("mac")) {
                os = OS.MAC;
            } else if (operSys.contains("sunos")) {
                os = OS.SOLARIS;
            }
        }
        return os;
    }

//        try {
//            TableModel model = table.getModel();
//        FileWriter excel = new FileWriter(file);
//
//        for (int i = 0; i < model.getColumnCount(); i++) {
//            excel.write(model.getColumnName(i) + "\t");
//        }
//
//        excel.write("\n");
//
//        for (int i = 0; i < model.getRowCount(); i++) {
//            for (int j = 0; j < model.getColumnCount(); j++) {
//                excel.write(model.getValueAt(i, j).toString() + "\t");
//            }
//            excel.write("\n");
//        }
//
//        excel.close();
//
//    }
//    catch (IOException e
//
//    
//        ) {
//            System.out.println(e);
//    }
//}
}
