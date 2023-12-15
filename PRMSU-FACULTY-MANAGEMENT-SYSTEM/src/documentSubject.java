import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class documentSubject {

    public static void createSubjectFolder(String facultyID, String lastName, String subjectName) {
        try {
            // Create the main directory for the faculty
            Path subjectDirectory = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyID + "-" + lastName + " - " + subjectName);
            Files.createDirectories(subjectDirectory);

            // Create subdirectory with subject name
            createSubdirectory(subjectDirectory, subjectName);

            System.out.println("Subject folder created successfully for " + facultyID + "-" + lastName + " - " + subjectName);
            System.out.println("Folder located at: " + subjectDirectory.resolve(subjectName).toAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to create subject folder for " + facultyID + "-" + lastName + " - " + subjectName);
        }
    }

    private static Path createSubdirectory(Path parent, String subdirectory) throws Exception {
        Path subdirectoryPath = parent.resolve(subdirectory);
        Files.createDirectories(subdirectoryPath);
        return subdirectoryPath;
    }
}