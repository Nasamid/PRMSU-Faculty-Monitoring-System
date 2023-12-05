import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.MediaSize.NA;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class PDFviewertest {
 
    public PDFviewertest() {
     
     File pdfFile = new File("PRMSU-FACULTY-MANAGEMENT-SYSTEM" + File.separator + "src" + File.separator + "Documents" + File.separator + "Testing.pdf");
     String pdfFilePath = pdfFile.getAbsolutePath();

     //System.out.println("File path: " + pdfFilePath);
     
     SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);

        JPanel viewerComponentPanel = factory.buildViewerPanel();
        viewerComponentPanel.setSize(500, 720);

        JPanel PreviewPanel = new JPanel();
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //applicationFrame.getContentPane().add(viewerComponentPanel);

        PreviewPanel.setBounds(50, 50, 450,575);
        PreviewPanel.add(viewerComponentPanel, BorderLayout.CENTER);
        
        controller.openDocument(pdfFilePath);

        applicationFrame.setLayout(null);
        applicationFrame.add(PreviewPanel);
        applicationFrame.setSize(1080, 720);
        applicationFrame.setVisible(true);
    }
    public static void main(String args[]){
    FlatMacLightLaf.setup();   
    new PDFviewertest();
    }
    
}