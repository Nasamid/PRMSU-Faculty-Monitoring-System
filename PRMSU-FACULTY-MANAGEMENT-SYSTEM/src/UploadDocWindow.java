import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.util.ArrayList;
import java.util.List;
import UploadDocTreeNodes.*;
import uk.ac.manchester.cs.bhig.jtreetable.JTreeTable;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class UploadDocWindow {

    JPanel TreeTablePanel, PreviewPanel, TopPanel;
    JLabel Department, DocPreviewText, AddFileText, DeleteFileText;
    JButton AddFileButton, DeleteFileButton;
    JComboBox<String> CFacultyName, CSchoolYear, CSemester;
    
    public UploadDocWindow(int facultyID){

        JFrame UploadDocFrame = new JFrame("Upload Documents");

        TreeTablePanel = new JPanel();
        PreviewPanel = new JPanel();
        TopPanel = new JPanel();

        Department = new JLabel();
        AddFileText = new JLabel();
        DeleteFileText = new JLabel();
        DocPreviewText = new JLabel("document preview",null,SwingConstants.CENTER);

        AddFileButton = new JButton();
        DeleteFileButton = new JButton();

        CFacultyName = new JComboBox<>();
        CSchoolYear = new JComboBox<>();
        CSemester = new JComboBox<>();

        SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller){
            @Override
            public JToolBar buildCompleteToolBar(boolean isEmbedded) {
                return null;
            }
        };
        
        JPanel viewerComponentPanel = factory.buildViewerPanel();
        viewerComponentPanel.setBounds(5,-100,440, 720);

        controller.getDocumentViewController().setAnnotationCallback(new org.icepdf.ri.common.MyAnnotationCallback(controller.getDocumentViewController()));

        TreeTablePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TreeTablePanel.setBounds(5,100,540, 575);
        TreeTablePanel.setLayout(new BorderLayout());
        //TreeTablePanel.setBackground();

        PreviewPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        PreviewPanel.setBounds(600,100,450, 575);
        PreviewPanel.setLayout(new BorderLayout());
        //PreviewPanel.setBackground();

        //TopPanel.setBorder(BorderFactory.createLineBorder(Color.gold));
        TopPanel.setBounds(0,0,1080, 75);
        TopPanel.setLayout(null);
        TopPanel.setBackground(new Color(255, 128, 41));

        DocPreviewText.setBounds(25, 25, 250,15);
        DocPreviewText.setFont(new Font("Arial", Font.BOLD, 15));

        File pdfFile = new File("PRMSU-FACULTY-MANAGEMENT-SYSTEM" + File.separator + "src" + File.separator + "Documents" + File.separator + "PDFVIEWER.pdf");
        String pdfFilePath = pdfFile.getAbsolutePath();

        //This code snipet opens the pdf in the pdf viewer and sets the zoom level
        controller.openDocument(pdfFilePath);
        float zoomLevel = 0.6f;
        controller.setZoom(zoomLevel);

        AddFileButton = new JButton();
        Image AddFileIcon;
                try {
                    AddFileIcon = ImageIO.read(LoginWindow.class.getResourceAsStream("/Images/AddSection25x.png"));
                    ImageIcon imageIcon = new ImageIcon(AddFileIcon);
                    AddFileButton.setIcon(imageIcon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        AddFileButton.setBounds(560,310,25,25);
        AddFileButton.setFocusable(false);
        AddFileButton.setContentAreaFilled(false);
        AddFileButton.setBorderPainted(false);
        AddFileButton.setBorder(null);
///////////////////////////////////////////////////////   ---->>>>>      ADD        FILE         BUTTON
        AddFileText.setText("Add File");
        AddFileText.setBounds(552, 340, 250,10);
        AddFileText.setForeground(new Color(75,174,79));
        AddFileText.setFont(new Font("Arial", Font.BOLD, 10));

        DeleteFileButton = new JButton();
        Image DeleteFileIcon;
            try {
                DeleteFileIcon = ImageIO.read(LoginWindow.class.getResourceAsStream("/Images/Delete25x.png"));
                ImageIcon imageIcon = new ImageIcon(DeleteFileIcon);
                DeleteFileButton.setIcon(imageIcon);                
            } catch (IOException e) {
                e.printStackTrace();
            }
        DeleteFileButton.setBounds(560,380,25,25);
        DeleteFileButton.setFocusable(false);
        DeleteFileButton.setContentAreaFilled(false);
        DeleteFileButton.setBorderPainted(false);
        DeleteFileButton.setBorder(null);

///////////////////////////////////////////////////////   ---->>>>>      DELETE        FILE         BUTTON

        DeleteFileText.setText("Delete File");
        DeleteFileText.setBounds(547, 410, 250,10);
        DeleteFileText.setForeground(new Color(255,68,68));
        DeleteFileText.setFont(new Font("Arial", Font.BOLD, 10));

        //This Section of the code is where the ComboBoxes are Located
        //String[] FacultyName = {};
        JLabel FacultyName = new JLabel();
        String[] SchoolYear = {"2020 - 2021","2021 - 2022","2022 - 2023","2023 - 2024", "2024 - 2025", "2025 - 2026", "2026 - 2027"};
        String[] Semester = {"501", "502", "503"};
        String[] Departments = {"601", "602", "603", "604", "605"};

        int topcompwidth = 200, yaxis = 25;
        
        FacultyName.setBounds(25,yaxis, topcompwidth, 25);
        FacultyName.setText(DatabaseHandler.getFullNameOfFaculty(facultyID));
        FacultyName.setForeground(Color.white );
        FacultyName.setFont(new Font("Arial", Font.BOLD, 15));
        
        String lastName = DatabaseHandler.getLastNameByFacultyID(facultyID);

        for (String Year : SchoolYear) {
            if (Year.substring(0,4).equals(DatabaseHandler.getAcademicYearOfFaculty(facultyID))){
                CSchoolYear.addItem(Year);
            break;
            }
        }

        for (String dept : Departments){
            if(dept.equals(DatabaseHandler.getDepartmentOfFaculty(facultyID))){
                if(dept == "601"){
                    Department.setText("Computer Engineering");
                    break;
                }
                else if(dept == "602"){
                    Department.setText("Mechanical Engineering");
                    break;
                }
                else if(dept == "603"){
                    Department.setText("Electrical Engineering");
                    break;
                }
                else if(dept == "604"){
                    Department.setText("Civil Engineering");
                    break;
                }
                else{
                    Department.setText("Allied");
                    break;
                }

            }

        }


        Department.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Department.setBackground(Color.white);
        Department.setOpaque(true);
        Department.setLayout(null);
        Department.setHorizontalAlignment(SwingConstants.CENTER);
        Department.setBounds(topcompwidth+50,yaxis,topcompwidth+145, 25);

        CSchoolYear.setBounds((topcompwidth*3+20),yaxis, topcompwidth, 25);
        
        for (String Sem : Semester) {
            if(Sem.equals(DatabaseHandler.getSemOfFaculty(facultyID))){
                if(Sem == "501"){
                CSemester.addItem("First Semester");
                break;
                }
                else if(Sem == "502"){
                CSemester.addItem("Second Semester");
                break;
                }
                else{
                CSemester.addItem("Midyear");
                break;
                }
            }
            
        }

        CSemester.setBounds((topcompwidth*4+10)+30,yaxis, topcompwidth, 25);
        //End of Combobox Section

        String facultyIDString = String.valueOf(DatabaseHandler.getFacultyIDByLastName(lastName));

        String filepath = documentfaculty.getPath(facultyIDString, lastName);

        String path = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyIDString + "-" + lastName).toString();
        System.out.println("file path is: "+path);
        File facultyFolder = new File(path);  // Update with the actual path
        
        DefaultMutableTreeNode rootNode = createTreeNodes(facultyFolder);
        String[] columnNames = {" ", "Status", "Date Submitted"}; // Adjust as needed

        CustomTreeTableModel treeTableModel = new CustomTreeTableModel(rootNode, columnNames);
        JXTreeTable treeTable = new JXTreeTable(treeTableModel);
        JScrollPane scrollPane = new JScrollPane(treeTable);
        
        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setValue((horizontalScrollBar.getMaximum() / 2) + 30 );
        

		List<String[]> content = new ArrayList<>();

        // //This Section is just temporary placeholder for what the table will contain
        // content.add(new String[] { "Load" });
        // content.add(new String[] { "Teaching Load", "Uploaded", "04/11/2023" });

		// content.add(new String[] { "Syllabus" });
		// content.add(new String[] { "Software Design", "Uploaded", "04/11/2023" });
        // content.add(new String[] { "Computer Aided Drafting", "Not Uploaded", "04/11/2023" });
        // content.add(new String[] { "Programming Logic and Design", "Not Uploaded", "04/11/2023" });

		// content.add(new String[] { "Class Record" });
        // content.add(new String[] { "Software Design", " " });
		// content.add(new String[] { "BSCpE 3A", "Uploaded", "04/11/2023" });
		// content.add(new String[] { "BSCpE 3B", "Not Uploaded", "04/11/2023" });
		// content.add(new String[] { "Computer Aided Drafting", " " });
		// content.add(new String[] { "BSCpE 3A", "Not Uploaded", "04/11/2023" });
		// content.add(new String[] { "BSCpE 3B", "Uploaded", "04/11/2023" });

        // content.add(new String[] { "Grade Sheet" });
        // content.add(new String[] { "Software Design", " " });
		// content.add(new String[] { "BSCpE 3A", "Uploaded", "04/11/2023" });
		// content.add(new String[] { "BSCpE 3B", "Not Uploaded", "04/11/2023" });
		// content.add(new String[] { "Computer Aided Drafting", " " });
		// content.add(new String[] { "BSCpE 3A", "Not Uploaded", "04/11/2023" });
		// content.add(new String[] { "BSCpE 3B", "Uploaded", "04/11/2023" });
        
        // content.add(new String[] { "Exam with Answer Key" });
        // content.add(new String[] { "Midterm", " " });
		// content.add(new String[] { "Software Design", "Uploaded", "04/11/2023" });
        // content.add(new String[] { "Computer Aided Drafting", "Not Uploaded", "04/11/2023" });
        // content.add(new String[] { "Programming Logic and Design", "Not Uploaded", "04/11/2023" });
		// content.add(new String[] { "Final", " " });
		// content.add(new String[] { "Software Design", "Uploaded", "04/11/2023" });
        // content.add(new String[] { "Computer Aided Drafting", "Not Uploaded", "04/11/2023" });
        // content.add(new String[] { "Programming Logic and Design", "Not Uploaded", "04/11/2023" });

        // content.add(new String[] { "Table of Specification" });
        // content.add(new String[] { "Midterm", " " });
		// content.add(new String[] { "Software Design", "Uploaded", "04/11/2023" });
        // content.add(new String[] { "Computer Aided Drafting", "Not Uploaded", "04/11/2023" });
        // content.add(new String[] { "Programming Logic and Design", "Not Uploaded", "04/11/2023" });
		// content.add(new String[] { "Final", " " });
		// content.add(new String[] { "Software Design", "Uploaded", "04/11/2023" });
        // content.add(new String[] { "Computer Aided Drafting", "Not Uploaded", "04/11/2023" });
        // content.add(new String[] { "Programming Logic and Design", "Not Uploaded", "04/11/2023" });

        // content.add(new String[] { "Item Analysis" });
        // content.add(new String[] { "Midterm", " " });
		// content.add(new String[] { "Software Design", "Uploaded", "04/11/2023" });
        // content.add(new String[] { "Computer Aided Drafting", "Not Uploaded", "04/11/2023" });
        // content.add(new String[] { "Programming Logic and Design", "Not Uploaded", "04/11/2023" });
		// content.add(new String[] { "Final", " " });
		// content.add(new String[] { "Software Design", "Uploaded", "04/11/2023" });
        // content.add(new String[] { "Computer Aided Drafting", "Not Uploaded", "04/11/2023" });
        // content.add(new String[] { "Programming Logic and Design", "Not Uploaded", "04/11/2023" });
        // //End of placeholder section

		//TreeTable treeTable = new TreeTable(content);
		UploadDocFrame.setLayout(new BorderLayout());

        TreeTablePanel.add(new JScrollPane(treeTable), BorderLayout.CENTER);
		//UploadDocFrame.add(new JScrollPane(treeTable.getTreeTable()), BorderLayout.CENTER);
        
        TopPanel.add(Department);
        TopPanel.add(FacultyName);
        TopPanel.add(CSchoolYear);
        TopPanel.add(CSemester);

        //PreviewPanel.add(scrollPane, BorderLayout.CENTER);
        PreviewPanel.add(DocPreviewText);

        UploadDocFrame.add(AddFileButton);
        UploadDocFrame.add(AddFileText);
        UploadDocFrame.add(DeleteFileButton);
        UploadDocFrame.add(DeleteFileText);

        UploadDocFrame.add(TreeTablePanel);
        UploadDocFrame.add(PreviewPanel);
        UploadDocFrame.add(TopPanel);

        Image logo;
            try {
                logo = ImageIO.read(UploadDocWindow.class.getResourceAsStream("/Images/UploadDocLogo16x.png"));
                UploadDocFrame.setIconImage(logo);
            } catch (IOException e) {
                e.printStackTrace();
            }

        //UploadDocFrame.setUndecorated(true);
        UploadDocFrame.setSize(1080, 720);
        UploadDocFrame.setResizable(false);
        UploadDocFrame.getContentPane().setLayout(null);    

        //This is the function that is responsible for making the window appear centered on startup
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = UploadDocFrame.getSize().width;
        int h = UploadDocFrame.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        UploadDocFrame.setLocation(x,y);
        UploadDocFrame.setVisible(true);
		UploadDocFrame.setVisible(true);


    }


    private DefaultMutableTreeNode createTreeNodes(File folder) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Add folder as parent node
                    DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(file.getName());
                    rootNode.add(parentNode);

                    // Read subfolders and files
                    readSubfoldersAndFiles(file, parentNode);
                }
            }
        }

        return rootNode;
    }


    private void readSubfoldersAndFiles(File folder, DefaultMutableTreeNode parentNode) {
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Add subfolder as child node
                    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file.getName());
                    parentNode.add(childNode);

                    // Read subfolders and files recursively
                    readSubfoldersAndFiles(file, childNode);
                } else {
                    // Add file information as child node
                    String fileName = file.getName();
                    String[] fileInfo = {fileName, null, null};  // Change this according to your structure
                    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(fileInfo);
                    parentNode.add(childNode);
                }
            }
        }
    }


    // public static void main(String[] args) {
    //     FlatMacLightLaf.registerCustomDefaultsSource("Properties");
    //     FlatMacLightLaf.setup();
    //     new UploadDocWindow();
    // }
}
