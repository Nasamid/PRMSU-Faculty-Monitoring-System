import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
public class documentfaculty {
    private String facultyIDString, lastName;

    public documentfaculty(String facultyIDString, String lastName) {
        this.facultyIDString = facultyIDString;
        this.lastName = lastName;
    }

    public void register() {
        try {
            // Create the main directory for the faculty
            Path facultyDirectory = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM","src", "Documents", "faculty", facultyIDString + "-" + lastName);
            Files.createDirectories(facultyDirectory);

            // Create subdirectories
            createSubdirectory(facultyDirectory, "load");
            createSubdirectory(facultyDirectory, "syllabus");
            createSubdirectory(facultyDirectory, "class record");
            createSubdirectory(facultyDirectory, "grade sheet");
            

            // Create subdirectories for exam-related folders
            createSubdirectory(facultyDirectory, "exam with answer key");
            createSubdirectory(facultyDirectory, "tables of specification");
            createSubdirectory(facultyDirectory, "item analysis");

            // Create subdirectories for specific exam-related folders
            createSubdirectory(facultyDirectory.resolve("exam with answer key"), "midterm");
            createSubdirectory(facultyDirectory.resolve("exam with answer key"), "finals");

            createSubdirectory(facultyDirectory.resolve("tables of specification"), "midterm");
            createSubdirectory(facultyDirectory.resolve("tables of specification"), "finals");

            createSubdirectory(facultyDirectory.resolve("item analysis"), "midterm");
            createSubdirectory(facultyDirectory.resolve("item analysis"), "finals");

            System.out.println("Registration successful for " + facultyIDString + "-" + lastName);
            System.out.println("Parent folder located at: " + facultyDirectory.toAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Registration failed for " + facultyIDString + "-" + lastName);
        }
    }

    private Path createSubdirectory(Path parent, String subdirectory) throws Exception {
        Path subdirectoryPath = parent.resolve(subdirectory);
        Files.createDirectories(subdirectoryPath);
        return subdirectoryPath;
    }

    public void deleteProfileFolder() {
        try {
            // Specify the path of the faculty's profile folder
            Path facultyDirectory = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM","src", "Documents", "faculty", facultyIDString + "-" + lastName);

            // Delete the directory and its contents recursively
            Files.walk(facultyDirectory)
                 .sorted(Comparator.reverseOrder())
                 .map(Path::toFile)
                 .forEach(File::delete);

            System.out.println("Profile folder deleted for " + facultyIDString);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to delete profile folder for " + facultyIDString);
        }
    }


    public static void main(String[] args) {
        // Example usage
        documentfaculty faculty = new documentfaculty("1","JohnDoe");
        faculty.register();
    }
}
