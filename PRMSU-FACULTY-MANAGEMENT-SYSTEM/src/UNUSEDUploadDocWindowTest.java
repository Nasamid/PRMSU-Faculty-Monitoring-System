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

public class UNUSEDUploadDocWindowTest {

    JPanel TreeTablePanel, PreviewPanel, TopPanel;
    JLabel Department, DocPreviewText, AddFileText, DeleteFileText;
    JButton AddFileButton, DeleteFileButton;
    JComboBox<String> CFacultyName, CSchoolYear, CSemester;
    
    public UNUSEDUploadDocWindowTest(int facultyID){

        //These are just some instantiations of GUI objects
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

        TreeTablePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        TreeTablePanel.setBounds(5,100,540, 575);
        TreeTablePanel.setLayout(new BorderLayout());

        //This gets the last name of the faculty from the faculty database via the facultyID assigned to them
        String lastName = DatabaseHandler.getLastNameByFacultyID(facultyID);

        //This gets and converts the faculty ID from the database into a String
        String facultyIDString = String.valueOf(DatabaseHandler.getFacultyIDByLastName(lastName));

        //This saves the file path of the faculty from the file explorer into a String ignore this since it's not used/relevant to the JXTreeTable it may be redundant since it's not used
        String filepath = documentfaculty.getPath(facultyIDString, lastName);

        //This gets the file path of the faculty from the file explorer based on the facultyIDString and the lastName
        String path = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyIDString + "-" + lastName).toString();
        System.out.println("file path is: "+path);

        //This saves the file path in to a File value
        File facultyFolder = new File(path);  // Update with the actual path
        
        //This instantiates the rootNode by calling the createTreeNodes using the facultyFolder
        DefaultMutableTreeNode rootNode = createTreeNodes(facultyFolder);
        //DefaultMutableTreeNode rootNode = createTreeNodes();

        //This is a list containing the names of each collumn of the JXTreeTable
        String[] columnNames = {" ", "Status", "Date Submitted"};

        //This instantiates the custom tree table model
        UNUSEDTreeTableModel treeTableModel = new UNUSEDTreeTableModel(rootNode, columnNames);

        //This inserts the custom model into the JXTreeTable and instantiates the JXTreetable
        JXTreeTable treeTable = new JXTreeTable(treeTableModel);


        treeTable.updateUI();

        //This instantitates a scrollPane and adds the JXTreeTable into it
        JScrollPane scrollPane = new JScrollPane(treeTable);
        
        //This gets the ScrollBar of the ScrollPane and sets it to the center of the JXTreeTable
        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setValue((horizontalScrollBar.getMaximum() / 2) + 30 );

		//This sets a new BorderLayout for the Frame of the entire JFrame
		UploadDocFrame.setLayout(new BorderLayout());

        //This adds the ScrollPane containing the JXTreeTable into the TreeTablePanel and adding a CENTERED border Layout
        TreeTablePanel.add(new JScrollPane(treeTable), BorderLayout.CENTER);

        //This adds the TreeTablePanel to the JFrame
        UploadDocFrame.add(TreeTablePanel);
        

        //These are just for the JFrame
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


    private List<UNUSEDFileNode> createFileNodes(File folder) {
        List<UNUSEDFileNode> fileNodes = new ArrayList<>();
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Recursively process subfolders
                    fileNodes.addAll(createFileNodes(file));
                } else {
                    // For each file, create a FileNode
                    String fileName = file.getName();
                    String status = "Status";  // Placeholder for status
                    String dateSubmitted = "Date Submitted";  // Placeholder for date submitted

                    UNUSEDFileNode fileNode = new UNUSEDFileNode(fileName, status, dateSubmitted);
                    fileNodes.add(fileNode);
                }
            }
        }

        return fileNodes;
    }

    private DefaultMutableTreeNode createTreeNodes(File folder) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        System.out.println("Creating tree nodes for: " + folder.getAbsolutePath());
        // Create FileNode instances for each file in the faculty folder
        List<UNUSEDFileNode> fileNodes = createFileNodes(folder);

        // Add FileNode instances to the tree
        for (UNUSEDFileNode fileNode : fileNodes) {
            
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(fileNode);
            rootNode.add(childNode);
        }

        return rootNode;
    }

    //This method creates the tree nodes from the directory using the File folder
    private DefaultMutableTreeNode createTreeNodes2(File folder) {
        //System.out.println("Creating tree nodes for: " + folder.getAbsolutePath());
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

    //This method reads from the created tree nodes and populates the JXTreeTable
    private void readSubfoldersAndFiles(File folder, DefaultMutableTreeNode parentNode) {
        //System.out.println("Reading subfolders and files for: " + folder.getAbsolutePath());
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


    //This is the Main Method for testing. The '2' in the instantiation of the window is the FacultyID from the database
    //The FlatLaf is just for aesthetic purposes
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatMacLightLaf.registerCustomDefaultsSource("Properties");
            FlatMacLightLaf.setup();
            new UNUSEDUploadDocWindowTest(1);
        });
    }
}
