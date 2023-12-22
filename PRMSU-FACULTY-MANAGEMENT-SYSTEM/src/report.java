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
import java.util.ArrayList;
import java.util.List;

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

        List<String[]> content = new ArrayList<>();
        List<FacultyData> facultyDataList = DatabaseHandler.getFacultyDataList();

        for (FacultyData facultyData : facultyDataList) {
            System.out.println("Faculty Name: " + facultyData.getFacultyName());
            System.out.println("Department Name: " + facultyData.getDepartmentName());
            System.out.println("Academic Year: " + facultyData.getAcademicYear());
            System.out.println("Semester Name: " + facultyData.getSemesterName());
            System.out.println("Faculty ID: " + DatabaseHandler.getFacultyID(facultyData.getFacultyName()));
            System.out.println("Last Name: " + DatabaseHandler.getLastNameByFacultyID(DatabaseHandler.getFacultyID(facultyData.getFacultyName())));
            System.out.println("----------------------");
        }

        for(FacultyData facultyData : facultyDataList){

            System.out.println(facultyDataList);

            int currentFacID = DatabaseHandler.getFacultyID(facultyData.getFacultyName());
            String currentFacName = facultyData.getFacultyName();
            String currentLastName =  DatabaseHandler.getLastNameByFacultyID(DatabaseHandler.getFacultyID(facultyData.getFacultyName()));
            String currentFacDept = facultyData.getDepartmentName();
            String currentFacSem = facultyData.getSemesterName();
            String currentFacAY = facultyData.getAcademicYear();
        
            String facultyIDString = Integer.toString(currentFacID);
            // System.out.println("Current Faculty ID: " + facultyIDString);
            // System.out.println("Current Faculty LastName: " + currentLastName);
            // System.out.println("Current Faculty Department: " + currentFacDept);
            // System.out.println("Current Faculty Semester: " + currentFacSem);
            // System.out.println("Current Faculty AY: " + currentFacAY);
            //This saves the file path of the faculty from the file explorer into a String ignore this since it's not used/relevant to the JXTreeTable it may be redundant since it's not used
            String filepath = documentfaculty.getPath(facultyIDString, currentLastName);

            //This gets the file path of the faculty from the file explorer based on the facultyIDString and the lastName
            String path = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyIDString + "-" + currentLastName).toString();
            System.out.println("file path is: "+path);

            //This saves the file path in to a File value
            File facultyFolder = new File(path);  // Update with the actual path

            scanDirectoryofFaculty(facultyFolder, currentFacName, currentFacDept,currentFacSem,currentFacAY, content, "", 0);

            addDataToTable(content);

        }



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

    //This method is responsible for populating the JXTreeTable by adding the directories into an Array list
    private void scanDirectoryofFaculty(File directory, String FacultyName, String department, String Semester, String AY, List<String[]> content, String parentPath, int depth) {
        String name = directory.getName();
        String currFile = "N/A";
        String status;
        String[] entry;
        
        Boolean ExistPDF = doesPdfExistInDirectory(directory);
        
        System.out.println("Current directory: " + directory);
        System.out.println("ExistPDF = " + ExistPDF);

        if (ExistPDF){
            status = "Submitted";
        }else{
            status = "Not Submitted";
        }


        if (directory.toString().contains("class")){
            currFile = "class";
        } else if (directory.toString().contains("load")){
            currFile = "load";
        } else if (directory.toString().contains("exam")){
            currFile = "exam";
        } else if (directory.toString().contains("grade")){
            currFile = "grade";
        } else if (directory.toString().contains("analysis")){
            currFile = "analysis";
        } else if (directory.toString().contains("syllabus")){
            currFile = "syllabus";
        } else if (directory.toString().contains("specification")){
            currFile = "specification";
        } 
    
        // Check if there are no more subdirectories within the current directory before adding the entry
        File[] innerSubDirectories = directory.listFiles(File::isDirectory);
        if (innerSubDirectories == null || innerSubDirectories.length == 0 && depth == 3 && ExistPDF != true) {
            entry = new String[]{FacultyName, department, currFile, Semester, AY, status,};
            //System.out.println("Entry: " + Arrays.toString(entry));
            content.add(entry);
            return; // Stop further recursion if this is the innermost subdirectory
        }
        if (innerSubDirectories == null || innerSubDirectories.length == 0 && depth == 2 && 
        (parentPath.equals("grade sheet") || parentPath.equals("syllabus") || 
        parentPath.equals("tables of specification") || parentPath.equals("load")) && ExistPDF != true) 
        {
            entry = new String[]{FacultyName, department, currFile, Semester, AY, status};
            //System.out.println("Entry: " + Arrays.toString(entry));
            content.add(entry);
            return; // Stop further recursion if this is the innermost subdirectory
        }
    
        if (directory.isDirectory()) {
            File[] subDirectories = directory.listFiles(File::isDirectory);
    
            // Check if subDirectories is neither null nor empty
            if (subDirectories != null && subDirectories.length > 0) {
                for (File subDir : subDirectories) {
                    scanDirectoryofFaculty(subDir, FacultyName, department,Semester, AY, content, name, depth + 1);
                }
            }
        }
    }


    // Method to check if a PDF file exists in the specified directory
    // Returns true if a PDF file is found, false otherwise
    private boolean doesPdfExistInDirectory(File directory) {
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

            return files != null && files.length > 0;
        }

        // If the directory doesn't exist or is not a directory, return false
        return false;
    }


    public void addDataToTable(List<String[]> dataList) {
        // Step 1: Convert List<String[]> to 2D array
        Object[][] dataArray = new Object[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i] = dataList.get(i);
        }

        // Step 2: Update Table Model
        updateTableModel(dataArray);
    }

    private void updateTableModel(Object[][] newData) {
        // Clear existing data in the table model
        model2.setRowCount(0);

        // Add new data to the table model
        for (Object[] row : newData) {
            model2.addRow(row);
        }

        // Notify the table that the model has changed
        model2.fireTableDataChanged();
    }


}