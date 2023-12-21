import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import UploadDocTreeNodes.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.SQLException;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.common.views.DocumentViewModel;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class UploadDocWindow {

    JPanel TreeTablePanel, PreviewPanel, TopPanel;
    JLabel Department, DocPreviewText, AddFileText, DeleteFileText;
    JButton AddFileButton, DeleteFileButton;
    JComboBox<String> CFacultyName, CSchoolYear, CSemester;
    String selectedPath = "";
    
    UploadDocWindow(int facultyID){

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

        JScrollPane scrollPane = new JScrollPane(viewerComponentPanel);
        scrollPane.setPreferredSize(new Dimension(450, 575));
        
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        

        File pdfFile = new File("PRMSU-FACULTY-MANAGEMENT-SYSTEM" + File.separator + "src" + File.separator + "Documents" + File.separator + "PDFVIEWER.pdf");
        
        
        String pdfFilePath = pdfFile.getAbsolutePath();

        //This code snipet opens the pdf in the pdf viewer and sets the zoom level
        controller.openDocument(pdfFilePath);
        float zoomLevel = 0.6f;
        controller.setZoom(zoomLevel);

        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setValue((horizontalScrollBar.getMaximum() / 2) + 30 );


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
        FacultyName.setForeground(Color.black);
        FacultyName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        FacultyName.setBackground(Color.white);
        FacultyName.setOpaque(true);
        FacultyName.setFont(new Font("Arial", Font.PLAIN, 12));
        FacultyName.setHorizontalAlignment(SwingConstants.CENTER);

        
        String lastName = DatabaseHandler.getLastNameByFacultyID(facultyID);
        System.out.println("Faculty ID: " + facultyID);

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

        Department.setText("Computer Engineering");
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

		List<String[]> content = new ArrayList<>();

        //This Section is just temporary placeholder for what the table will contain
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
        //End of placeholder section

        //This gets and converts the faculty ID from the database into a String
        String facultyIDString = Integer.toString(facultyID);
        System.out.println("FacultyIDString: " + facultyIDString);
        //This saves the file path of the faculty from the file explorer into a String ignore this since it's not used/relevant to the JXTreeTable it may be redundant since it's not used
        String filepath = documentfaculty.getPath(facultyIDString, lastName);

        //This gets the file path of the faculty from the file explorer based on the facultyIDString and the lastName
        String path = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyIDString + "-" + lastName).toString();
        System.out.println("file path is: "+path);

        //This saves the file path in to a File value
        File facultyFolder = new File(path);  // Update with the actual path

        scanDirectory(facultyFolder, content, "", 0);

        // // Print the contents of the ArrayList
        // for (String[] entry : content) {
        //     for (String element : entry) {
        //         System.out.print(element + " ");
        //     }
        //     System.out.println();
        // }


		TreeTable treeTable = new TreeTable(content);
		UploadDocFrame.setLayout(new BorderLayout());

        TreeTablePanel.add(new JScrollPane(treeTable.getTreeTable()), BorderLayout.CENTER);
		//UploadDocFrame.add(new JScrollPane(treeTable.getTreeTable()), BorderLayout.CENTER);
        
        TopPanel.add(Department);
        TopPanel.add(FacultyName);
        TopPanel.add(CFacultyName);
        TopPanel.add(CSchoolYear);
        TopPanel.add(CSemester);

        PreviewPanel.add(scrollPane, BorderLayout.CENTER);
        //PreviewPanel.add(DocPreviewText);

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

        treeTable.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                TreePath[] paths = e.getPaths();
        
                if (paths.length > 0) {
                    TreePath newestPath = paths[0];
                    selectedPath = treeTable.treePathLastComponentToString(newestPath);
                    System.out.println("Selected Path: " + selectedPath);
        
                    // Look for the first PDF file in the selected directory
                    File pdfFile = findFirstPdfInDirectory(selectedPath);
        
                    if (pdfFile != null && pdfFile.exists()) {
                        // Pass the existing controller to update the viewer
                        openPdf(pdfFile, controller);
                    } else {
                        // Use default PDF file if no PDF found in the selected directory
                        File defaultPdfFile = new File("PRMSU-FACULTY-MANAGEMENT-SYSTEM" + File.separator + "src" + File.separator + "Documents" + File.separator + "PDFVIEWER.pdf");
                        
                        // Pass the existing controller to update the viewer
                        openPdf(defaultPdfFile, controller);
                    }
                }
            }
        });

        AddFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a file chooser
                JFileChooser fileChooser = new JFileChooser();
        
                // Create a file filter for text files (customize based on your needs)
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files", "pdf");
                fileChooser.setFileFilter(filter);
        
                // Show the file chooser dialog
                int result = fileChooser.showOpenDialog(null);
        
                // Check if a file was selected
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Get the selected file
                    java.io.File selectedFile = fileChooser.getSelectedFile();

                    // Construct the target path by combining the selected directory and file name
                    Path targetPath = Path.of(selectedPath, selectedFile.getName());
                    

                    try {
                        // Copy the file to the target directory
                        Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("File copied to: " + targetPath);
                        boolean uploaded_cr = false;
                        boolean uploaded_load = false;
                        boolean  uploaded_exam = false;
                        boolean uploaded_grade = false;
                        boolean uploaded_analysis = false;
                        boolean uploaded_syllabus = false;
                        boolean uploaded_specs = false;

                        Connection connection = DatabaseHandler.connect();
                        try {
                            System.out.println(targetPath.toString());
                            if(targetPath.toString().contains("class")){
                                uploaded_cr = DatabaseHandler.insertClassRecordToDatabase(connection, facultyID, targetPath.toString());
                                uploaded_cr = DatabaseHandler.insertfilesPopulator(connection, facultyID, selectedPath);
                            }
                            else if(targetPath.toString().contains("load")){
                                uploaded_load = DatabaseHandler.insertLoadToDatabase(connection, facultyID, targetPath.toString());
                                uploaded_load = DatabaseHandler.insertfilesPopulator(connection, facultyID, selectedPath);
                            }
                            else if(targetPath.toString().contains("exam")){
                                uploaded_exam = DatabaseHandler.insertExamToDatabase(connection, facultyID, targetPath.toString());
                                uploaded_exam = DatabaseHandler.insertfilesPopulator(connection, facultyID, selectedPath);
                            }
                            else if(targetPath.toString().contains("grade")){
                                uploaded_grade = DatabaseHandler.insertGradeSheetToDatabase(connection, facultyID, targetPath.toString());
                                uploaded_grade = DatabaseHandler.insertfilesPopulator(connection, facultyID, selectedPath);
                            }
                            else if(targetPath.toString().contains("analysis")){
                                uploaded_analysis = DatabaseHandler.insertItemAnalysisToDatabase(connection, facultyID, targetPath.toString());
                                uploaded_analysis = DatabaseHandler.insertfilesPopulator(connection, facultyID, selectedPath);
                            }
                            else if(targetPath.toString().contains("syllabus")){
                                uploaded_syllabus = DatabaseHandler.insertSyllabusToDatabase(connection, facultyID, targetPath.toString());
                                uploaded_syllabus = DatabaseHandler.insertfilesPopulator(connection, facultyID, selectedPath);
                            }
                            else if(targetPath.toString().contains("specification")){
                                uploaded_specs = DatabaseHandler.insertSpecsToDatabase(connection, facultyID, targetPath.toString());
                                uploaded_specs = DatabaseHandler.insertfilesPopulator(connection, facultyID, selectedPath);
                            }
                            else{
                                System.out.println("File not uploaded");
                            }
                            
                        //NEED TO IMPLEMENT REFRESH FUNCTION AFTER ADDING
                        
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        //ONCE THE FILE HAS BEEN ADDED WE NEED IT TO BE FLAGGED INTO THE DATABASE WITH THE CURRENT DATE AND UPLOADED STATUS = TRUE


                    } catch (FileAlreadyExistsException faee) {
                        System.out.println("File already exists in the target directory: " + targetPath);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
        
                    // Print the path of the selected file (you can handle it as needed)
                    System.out.println("Selected File: " + selectedFile.getAbsolutePath() + " Copied to: " + selectedPath);
                } else {
                    System.out.println("No file selected");
                }
            }
        });

        DeleteFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check if the selectedPath is not empty
                if (!selectedPath.isEmpty()) {
                    // Construct the Path object for the selected directory
                    Path directoryPath = Path.of(selectedPath);
        
                    // Debugging: Print selectedPath before deletion
                    System.out.println("Deleting files in directory: " + selectedPath);
        
                    List<Path> filesToDelete = new ArrayList<>();
        
                    try {
                        // Walk through the directory and collect files to delete
                        Files.walkFileTree(directoryPath, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<>() {
                            @Override
                            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                // Use default PDF file if no PDF found in the selected directory
                                File defaultPdfFile = new File("PRMSU-FACULTY-MANAGEMENT-SYSTEM" + File.separator + "src" + File.separator + "Documents" + File.separator + "PDFVIEWER.pdf");
        
                                // Pass the existing controller to update the viewer
                                openPdf(defaultPdfFile, controller);
        
                                // Debugging: Print each file before deletion
                                System.out.println("Deleting file: " + file);
                                filesToDelete.add(file);
                                return FileVisitResult.CONTINUE;
                            }
                        });
        
                        // Delete collected files
                        for (Path fileToDelete : filesToDelete) {
                            Files.delete(fileToDelete);
                            System.out.println("Deleted file: " + fileToDelete);
                        }
        
                        System.out.println("All files in directory deleted: " + selectedPath);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    System.out.println("No directory selected");
                }
            }
        });


        DeleteFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check if the selectedPath is not empty
                if (!selectedPath.isEmpty()) {
                    // Construct the Path object for the selected directory
                    Path directoryPath = Path.of(selectedPath);
        
                    // Debugging: Print selectedPath before deletion
                    System.out.println("Deleting files in directory: " + selectedPath);
        
                    List<Path> filesToDelete = new ArrayList<>();
        
                    try {
                        Files.walkFileTree(directoryPath, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
                            @Override
                            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                // Use default PDF file if no PDF found in the selected directory
                                File defaultPdfFile = new File("PRMSU-FACULTY-MANAGEMENT-SYSTEM" + File.separator + "src" + File.separator + "Documents" + File.separator + "PDFVIEWER.pdf");
                    
                                // Pass the existing controller to update the viewer
                                openPdf(defaultPdfFile, controller);
                    
                                // Debugging: Print each file before deletion
                                System.out.println("Deleting file: " + file);
                                filesToDelete.add(file);
                    
                                return FileVisitResult.CONTINUE;
                            }
                        });
                    
                        // Delete collected files
                        for (Path fileToDelete : filesToDelete) {
                            try {
                                Files.delete(fileToDelete);
                                System.out.println("Deleted file: " + fileToDelete);
                            } catch (IOException deleteException) {
                                System.err.println("Error deleting file " + fileToDelete + ": " + deleteException.getMessage());
                            }
                        }
                    
                        System.out.println("All files in directory deleted: " + selectedPath);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    System.out.println("No directory selected");
                }
            }
        });

        

    }


    //This method is responsible for populating the JXTreeTable by adding the directories into an Array list
    private void scanDirectory(File directory, List<String[]> content, String parentPath, int depth) {
        String name = directory.getName();
        String status;
        String[] entry;
        String date;
        
        Boolean ExistPDF = doesPdfExistInDirectory(directory);
        
        System.out.println("Current directory: " + directory);
        System.out.println("ExistPDF = " + ExistPDF);

        if (ExistPDF){
            status = "Submitted";
            date = DatabaseHandler.getfilesDate(directory);
        }else{
            status = "Not Submitted";
            date = "N/A";
        }
    
        // Check if there are no more subdirectories within the current directory before adding the entry
        File[] innerSubDirectories = directory.listFiles(File::isDirectory);
        if (innerSubDirectories == null || innerSubDirectories.length == 0 && depth == 3) {
            entry = new String[]{name, status, date, Integer.toString(depth), "File Folder", directory.getPath()};
            //System.out.println("Entry: " + Arrays.toString(entry));
            content.add(entry);
            return; // Stop further recursion if this is the innermost subdirectory
        }
        if (innerSubDirectories == null || innerSubDirectories.length == 0 && depth == 2 && 
        (parentPath.equals("grade sheet") || parentPath.equals("syllabus") || 
        parentPath.equals("tables of specification") || parentPath.equals("load"))) 
        {
            entry = new String[]{name, status, date, Integer.toString(depth), "File Folder", directory.getPath()};
            //System.out.println("Entry: " + Arrays.toString(entry));
            content.add(entry);
            return; // Stop further recursion if this is the innermost subdirectory
        }
    
        if (directory.isDirectory()) {
            if (!parentPath.isEmpty()) {
                entry = new String[]{name, " ", " ", Integer.toString(depth), directory.getPath()};
            } else {
                entry = new String[]{name, " "," ", Integer.toString(depth), " ", parentPath, directory.getPath()};
            }
            //System.out.println("Entry: " + Arrays.toString(entry));
            content.add(entry);
    
            File[] subDirectories = directory.listFiles(File::isDirectory);
    
            // Check if subDirectories is neither null nor empty
            if (subDirectories != null && subDirectories.length > 0) {
                for (File subDir : subDirectories) {
                    scanDirectory(subDir, content, name, depth + 1);
                }
            }
        }
    }
    
    
    // File[] files = directory.listFiles(file -> file.isFile() && file.getName().endsWith(".pdf"));
            // if (files != null) {
            //     for (File file : files) {
            //         entry = new String[]{file.getName(), "not Submitted", "20/12/2023", file.getPath()};
            //         content.add(entry);
            //     }
            // }
    
    

    //  // Define a simple FileVisitor to handle file deletion
    // private static class SimpleFileVisitor implements java.nio.file.FileVisitor<Path> {
    //     @Override
    //     public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    //         try {
    //             Files.delete(file);
    //             System.out.println("Deleted file: " + file);
    //         } catch (IOException e) {
    //             System.err.println("Error deleting file " + file + ": " + e.getMessage());
    //         }
    //         return FileVisitResult.CONTINUE;
    //     }

    //     @Override
    //     public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    //         // Handle the case where the visit of a file fails
    //         System.err.println("Failed to visit: " + file.getFileName() + ", Reason: " + exc.getMessage());
    //         return FileVisitResult.CONTINUE;
    //     }

    //     @Override
    //     public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
    //         return FileVisitResult.CONTINUE;
    //     }

    //     @Override
    //     public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    //         // Handle the case after visiting a directory
    //         return FileVisitResult.CONTINUE;
    //     }
    // }

    // Method to find the first PDF file in the specified directory
    private File findFirstPdfInDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

            if (files != null && files.length > 0) {
                // Return the first PDF file found in the directory
                return files[0];
            }
        }

        return null;
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

    // Method to open the specified PDF file using IcePDF
    private void openPdf(File pdfFile, SwingController controller) {
        // Close the existing document (if any)
        controller.closeDocument();

        // Set up the new document
        String pdfFilePath = pdfFile.getAbsolutePath();
        controller.openDocument(pdfFilePath);

        // Perform any additional setup if needed
        float zoomLevel = 0.6f;
        controller.setZoom(zoomLevel);

        // Repaint the viewer
        controller.getDocumentViewController().getViewContainer().revalidate();
        controller.getDocumentViewController().getViewContainer().repaint();

        // Add the rest of your code for setting up the viewer as needed
        // ...
    }

    public static void main(String[] args) {
        FlatMacLightLaf.registerCustomDefaultsSource("Properties");
        FlatMacLightLaf.setup();
        new UploadDocWindow(18);
    }
}
