import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class HomeWindow {
        JFrame HomeWindow;
    
        HomeWindow(){

            //Dito Codes for HomePage
            HomeWindow = new JFrame();

            HomeWindow.setTitle("COE Faculty Monitoring System");
            HomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            HomeWindow.setVisible(true);
            HomeWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
            HomeWindow.setResizable(false);
        
        }
}
