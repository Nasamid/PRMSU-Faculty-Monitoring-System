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
            Path facultyDirectory = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyIDString + "-" + lastName);
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
            // createSubdirectory(facultyDirectory.resolve("exam with answer key"), "midterm");
            // createSubdirectory(facultyDirectory.resolve("exam with answer key"), "finals");

            // createSubdirectory(facultyDirectory.resolve("tables of specification"), "midterm");
            // createSubdirectory(facultyDirectory.resolve("tables of specification"), "finals");

            // createSubdirectory(facultyDirectory.resolve("item analysis"), "midterm");
            // createSubdirectory(facultyDirectory.resolve("item analysis"), "finals");

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

    public void createSubjectFolderInSubdirectories(String subjectName) {
        try {
            // Specify the path of the faculty's profile folder
            Path facultyDirectory = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyIDString + "-" + lastName);

            // Specify the subdirectories where the subject folder needs to be created
            String[] subdirectories = {"class record", "grade sheet", "syllabus", "exam with answer key", "item analysis", "tables of specification"};

            for (String subdirectory : subdirectories) {
                // Create the subject folder inside each subdirectory
                if(subdirectory.equals("exam with answer key") || subdirectory.equals("item analysis") || subdirectory.equals("table of specification")){
                    Path subjectDirectory = facultyDirectory.resolve(subdirectory).resolve("Midterms").resolve(subjectName);
                    Path subjectDirectory2 = facultyDirectory.resolve(subdirectory).resolve("Finals").resolve(subjectName);
                    Files.createDirectories(subjectDirectory);
                    Files.createDirectories(subjectDirectory2);
                }
                if(subdirectory.equals("load")){
                    Path subjectDirectory = facultyDirectory.resolve(subdirectory).resolve("Teaching Load").resolve(subjectName);
                    Files.createDirectories(subjectDirectory);
                }
                else{
                    Path subjectDirectory = facultyDirectory.resolve(subdirectory).resolve(subjectName);
                    Files.createDirectories(subjectDirectory);
                }
                

                System.out.println("Subject folder created for " + facultyIDString + "-" + lastName + " - " + subjectName + " in " + subdirectory);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to create subject folder for " + facultyIDString + "-" + lastName + " - " + subjectName);
        }
    }

    public void renameSubjectFolders(String courseCode, String courseDescription, String newCode, String newDescription) {
        try {
            // Specify the path of the faculty's profile folder
            Path facultyDirectory = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyIDString + "-" + lastName);
    
            // Specify the subdirectories where the subject folders need to be renamed
            String[] subdirectories = {"class record", "grade sheet", "syllabus", "exam with answer key", "item analysis", "tables of specification"};
    
            for (String subdirectory : subdirectories) {
                // Rename the subject folders inside each subdirectory
                if (subdirectory.equals("exam with answer key") || subdirectory.equals("item analysis") || subdirectory.equals("tables of specification")) {
                    Path oldSubjectDirectoryMidterm = facultyDirectory.resolve(subdirectory).resolve("Midterms").resolve(courseCode + " - " + courseDescription);
                    Path newSubjectDirectoryMidterm = facultyDirectory.resolve(subdirectory).resolve("Midterms").resolve(newCode + " - " + newDescription);
    
                    Files.createDirectories(newSubjectDirectoryMidterm); // Ensure parent directory exists
                    Files.move(oldSubjectDirectoryMidterm, newSubjectDirectoryMidterm, StandardCopyOption.REPLACE_EXISTING);
    
                    // Delete the old directory after moving its contents
                    //Files.delete(oldSubjectDirectoryMidterm);
                    
                    Path oldSubjectDirectoryFinals = facultyDirectory.resolve(subdirectory).resolve("Finals").resolve(courseCode + " - " + courseDescription);
                    Path newSubjectDirectoryFinals = facultyDirectory.resolve(subdirectory).resolve("Finals").resolve(newCode + " - " + newDescription);
    
                    Files.createDirectories(newSubjectDirectoryFinals); // Ensure parent directory exists
                    Files.move(oldSubjectDirectoryFinals, newSubjectDirectoryFinals, StandardCopyOption.REPLACE_EXISTING);
    
                    // Delete the old directory after moving its contents
                    //Files.delete(oldSubjectDirectoryFinals);

                } else {
                    Path oldSubjectDirectory = facultyDirectory.resolve(subdirectory).resolve(courseCode + " - " + courseDescription);
                    Path newSubjectDirectory = facultyDirectory.resolve(subdirectory).resolve(newCode + " - " + newDescription);
    
                    Files.createDirectories(newSubjectDirectory); // Ensure parent directory exists
                    Files.move(oldSubjectDirectory, newSubjectDirectory, StandardCopyOption.REPLACE_EXISTING);
    
                    // Delete the old directory after moving its contents
                    //Files.delete(oldSubjectDirectory);
                }
    
                System.out.println("Subject folder renamed for " + facultyIDString + "-" + lastName + " - " + courseCode + " - " + courseDescription + " to " + newCode + " - " + newDescription + " in " + subdirectory);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to rename subject folder for " + facultyIDString + "-" + lastName + " - " + courseCode + " - " + courseDescription + " to " + newCode + " - " + newDescription);
        }
    }

    public static String getPath(String facultyIDString, String lastName){
        String path = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyIDString + "-" + lastName, "load").toString();
        return path;
    }
    
    

    public void createSectionFolderInSubject(String subjectName, String sectionName) {
        try {
            // Specify the path of the faculty's profile folder
            Path facultyDirectory = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyIDString + "-" + lastName);

            // Create the section folder inside the subject folder
            Path sectionDirectory = facultyDirectory.resolve("class record").resolve(subjectName).resolve(sectionName);
            Files.createDirectories(sectionDirectory);

            System.out.println("Section folder created for " + facultyIDString + "-" + lastName + " - " + subjectName + " - " + sectionName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to create section folder for " + facultyIDString + "-" + lastName + " - " + subjectName + " - " + sectionName);
        }
    }

    
    public void deleteSubjectFolderInSubdirectories(String subjectName) {
        try {
            // Specify the path of the faculty's profile folder
            Path facultyDirectory = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyIDString + "-" + lastName);

            // Specify the subdirectories where the subject folder needs to be created
            String[] subdirectories = {"class record", "grade sheet", "syllabus", "exam with answer key", "item analysis", "tables of specification"};

            for (String subdirectory : subdirectories) {
                // Create the subject folder inside each subdirectory
                if(subdirectory.equals("exam with answer key") || subdirectory.equals("item analysis") || subdirectory.equals("table of specification")){
                    Path subjectDirectory = facultyDirectory.resolve(subdirectory).resolve("Midterms").resolve(subjectName);
                    Path subjectDirectory2 = facultyDirectory.resolve(subdirectory).resolve("Finals").resolve(subjectName);
                    Files.delete(subjectDirectory);
                    Files.delete(subjectDirectory2);
                }
                else{
                    Path subjectDirectory = facultyDirectory.resolve(subdirectory).resolve(subjectName);
                    Files.delete(subjectDirectory);
                }
                

                System.out.println("Subject folder deleted for " + facultyIDString + "-" + lastName + " - " + subjectName + " in " + subdirectory);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to delete subject folder for " + facultyIDString + "-" + lastName + " - " + subjectName);
        }
    }

    public void deleteProfileFolder() {
        try {
            // Specify the path of the faculty's profile folder
            Path facultyDirectory = Paths.get("PRMSU-FACULTY-MANAGEMENT-SYSTEM", "src", "Documents", "faculty", facultyIDString + "-" + lastName);

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

    // public static void main(String[] args) {
    //     // Example usage
    //     documentfaculty faculty = new documentfaculty("1", "JohnDoe");
    //     faculty.register();
    //     faculty.createSubjectFolderInSubdirectories("Mathematics");
    //     faculty.createSectionFolderInSubject("Mathematics", "SectionA");
    // }
}
