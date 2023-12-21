import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
public class report{
        JPanel panel;
        DefaultTableModel model2;
        JTable table2;
        JScrollPane scrollPane2;
        JLabel report;
        JButton button2;
        JTextField Search2;

    private ActionListener exportToExcel = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            int userSelection = fileChooser.showSaveDialog(null);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try (FileWriter writer = new FileWriter(fileToSave + ".csv")) {
                    for (int i = 0; i < model2.getColumnCount(); i++) {
                        writer.write(model2.getColumnName(i) + ",");
                    }
                    writer.write("\n");
                    for (int row = 0; row < model2.getRowCount(); row++) {
                        for (int col = 0; col < model2.getColumnCount(); col++) {
                            writer.write(model2.getValueAt(row, col) + ",");
                        }
                        writer.write("\n");
                    }
                    writer.close();
                    JOptionPane.showMessageDialog(null,
                            "Data exported to " + fileToSave.getAbsolutePath() + ".csv");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error exporting to CSV: " + ex.getMessage());
                }
            }
        }
    };
    static class ClickedCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // Set the gradient background color for clicked cells
             cell.setBackground(new Color(0,0,0));
            // Override the default selection color to be transparent
            if (isSelected) {
                cell.setBackground(new Color(255, 255, 0, 50)); // Adjust the alpha (last parameter) as needed
            }
    
            return cell;
        }
    }
     report(){
        panel = new JPanelGradient();
        panel.setLayout(null);
        //Color variable
        Color complimentColor = new Color(255, 128, 41);

        String[] columnNames2 = {"Name","Department","File","Semester","Academic Year","Status"};
        // Data
        Object[][] data2 = {
        };
        
        //table model
        model2 = new DefaultTableModel(data2, columnNames2){
        @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        
        class CenterRenderer2 extends DefaultTableCellRenderer {
             public CenterRenderer2() {
        setHorizontalAlignment(JLabel.CENTER);
        }
    }
         
        //table with the model
        table2 = new JTable(model2);
        table2.setBounds(10, 10, 100, 100);
        table2.getTableHeader().setBounds(0,0, 50,30);
        table2.getTableHeader().setFont(new Font("ARIAL",Font.BOLD,16));
        table2.getTableHeader().setBackground(complimentColor);
        table2.setGridColor(Color.BLACK);
        table2.setShowGrid(true);
        table2.setRowHeight(45);
        table2.setFont(new Font("ARIAL", Font.PLAIN, 13));
        table2.getTableHeader().setReorderingAllowed(false);
        table2.getTableHeader().setResizingAllowed(false);
        //scroll pane
        scrollPane2 = new JScrollPane(table2);
        table2.setPreferredScrollableViewportSize(new Dimension(975,600));
        scrollPane2.setBounds(10, 50, 975, 600);
        
        //Rendere of each input in table
        CenterRenderer2 centerRenderer2 = new CenterRenderer2();
        for (int i = 0; i < table2.getColumnCount(); i++) {
         table2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer2);
        }
        
        //report
        report = new JLabel("REPORT");
        report.setBounds(30, 10, 200, 30);
        report.setFont(new Font("ARIAL", Font.BOLD, 30));
        
        //button
        button2 = new JButton("Export");
        // button2.setBackground(complimentColor);
        button2.setForeground(Color.BLACK);
        button2.setBounds(880, 660, 100, 30);
        button2.addActionListener(exportToExcel);
        
        //table panel
        panel.setBounds(180,0,1000,720);
        panel.add(scrollPane2);
        panel.add(report);
        panel.add(button2);
    }
    //This class is to add gradient to the JPanels
    class JPanelGradient extends JPanel{
        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color C1 = new Color(255, 198, 43);
            Color C2 = new Color(255, 77, 41);
            GradientPaint gp = new GradientPaint(75,0,C1,180,height,C2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
            
        }
    }
}