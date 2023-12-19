import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.FileSystemModel;

import javax.swing.*;
import java.io.File;
import java.nio.file.Paths;

public class FileSystemTreeExample {
    public static void main(String[] args) {

            JFrame frame = new JFrame("File System Tree Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //This gets the last name of the faculty from the faculty database via the facultyID assigned to them
        String lastName = DatabaseHandler.getLastNameByFacultyID(1);

        //This gets and converts the faculty ID from the database into a String
        String facultyIDString = String.valueOf(DatabaseHandler.getFacultyIDByLastName(lastName));

        //This saves the file path of the faculty from the file explorer into a String ignore this since it's not used/relevant to the JXTreeTable it may be redundant since it's not used
        String filepath = documentfaculty.getPath(facultyIDString, lastName);

        //This gets the file path of the faculty from the file explorer based on the facultyIDString and the lastName
        String path = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyIDString + "-" + lastName).toString();
        System.out.println("file path is: "+path);

        //This saves the file path in to a File value
        File facultyFolder = new File(path);  // Update with the actual path
        File testPath = new File("C:\\Users\\Administrator\\Desktop");

            // Create a JXTreeTable with FileSystemModel
            JXTreeTable treeTable = new JXTreeTable(new CustomTreeTableModel2(testPath));

            // Add the JXTreeTable to the frame
            frame.getContentPane().add(new JScrollPane(treeTable));
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
    }
}
