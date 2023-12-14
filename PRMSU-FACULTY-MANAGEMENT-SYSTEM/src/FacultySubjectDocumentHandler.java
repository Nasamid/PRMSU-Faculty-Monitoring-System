import java.nio.file.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultySubjectDocumentHandler {

    private int facultyID;
    private String lastName;

    public FacultySubjectDocumentHandler(int facultyID) {
        this.facultyID = facultyID;
        this.lastName = getLastNameByFacultyID(facultyID);
    }

    public void createSubjectFolders() {
        try {
            // Create the main directory for the faculty if not exists
            Path facultyDirectory = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyID + "-" + lastName);
            Files.createDirectories(facultyDirectory);

            // Get the subjects associated with the faculty
            Connection connection = DatabaseHandler.connect();
            String query = "SELECT DISTINCT subjectName FROM faculty_subject_section WHERE facultyID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, facultyID);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String subjectName = resultSet.getString("subject");
                    // Create subdirectory with subject name
                    createSubdirectory(facultyDirectory, subjectName);
                }
            }

            System.out.println("Subject folders created for faculty " + facultyID + "-" + lastName);
            System.out.println("Parent folder located at: " + facultyDirectory.toAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to create subject folders for faculty " + facultyID + "-" + lastName);
        }
    }

    private Path createSubdirectory(Path parent, String subdirectory) throws Exception {
        Path subdirectoryPath = parent.resolve(subdirectory);
        Files.createDirectories(subdirectoryPath);
        return subdirectoryPath;
    }

    public static String getLastNameByFacultyID(int facultyID) {
        String lastName = null;

        String query = "SELECT lastName FROM fID_lastName WHERE facultyID = ?";

        try (Connection connection = DatabaseHandler.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, facultyID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lastName = resultSet.getString("lastName");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastName;
    }

    public static void main(String[] args) {
        // Example usage
        FacultySubjectDocumentHandler handler = new FacultySubjectDocumentHandler(1);
        handler.createSubjectFolders();
    }
}
