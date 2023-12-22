import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler {

    public static Connection connect() {
        Connection connection = null;
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // JDBC URL for SQLite
            String url = "jdbc:sqlite:main.db";

            // Create the connection
            connection = DriverManager.getConnection(url);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return connection;
    }

    public static int getFssID(int facultyID, int subjectID) {
        int fssID = -1;

        String query = "SELECT fssID FROM faculty_subject_section WHERE facultyID = ? AND subjectID = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, facultyID);
            preparedStatement.setInt(2, subjectID);


            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fssID = resultSet.getInt("fssID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fssID;
    }


    public static int getSectionIDByFacultySubject(int facultyID, int subjectID) {
        int sectionID = -1;

        String query = "SELECT sectionID FROM faculty_subject_section WHERE facultyID = ? AND subjectID = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, facultyID);
            preparedStatement.setInt(2, subjectID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sectionID = resultSet.getInt("sectionID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sectionID;
    }


    // Insert faculty data into the database
    public static int insertFaculty(String facultyName, int departmentID, int yearID, int semesterID) {
        String query = "INSERT INTO faculty (name, deptID, yearID, semesterID) VALUES (?, ?, ?, ?)";
    
        try (Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, facultyName);
            preparedStatement.setInt(2, departmentID);
            preparedStatement.setInt(3, yearID);
            preparedStatement.setInt(4, semesterID);
    
            int affectedRows = preparedStatement.executeUpdate();
    
            if (affectedRows > 0) {
                // Retrieve the generated facultyID
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int facultyID = generatedKeys.getInt(1);
    
                    return facultyID;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return -1; // Return -1 if the insertion fails
    }
    
    // Get department ID from the database based on department name
    public static int getDepartmentID(String departmentName) {
        String query = "SELECT deptID FROM department WHERE departmentName = ?";
        int departmentID = -1;

        try (Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, departmentName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                departmentID = resultSet.getInt("deptID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departmentID;
    }

    public static boolean insertClassRecordToDatabase(Connection connection, int facultyID, String path) throws SQLException {
        String insertQuery = "INSERT INTO class_record (facultyID, path, date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            // Set parameters for the SQL query
            preparedStatement.setInt(1, facultyID);
            preparedStatement.setString(2, path);
            
            // Set the current date in MM/DD/YYYY format
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String currentDate = dateFormat.format(new Date());
            preparedStatement.setString(3, currentDate);

            // Set the status (1 for true, 0 for false)
            preparedStatement.setInt(4, 1);

            // Execute the update
            preparedStatement.executeUpdate();
            System.out.println("Class record added to the database.");

            return true;
        }
    }

    public static boolean insertExamToDatabase(Connection connection, int facultyID, String path) throws SQLException {
        String insertQuery = "INSERT INTO exam_anskey (facultyID, path, date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            // Set parameters for the SQL query
            preparedStatement.setInt(1, facultyID);
            preparedStatement.setString(2, path);
            
            // Set the current date in MM/DD/YYYY format
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String currentDate = dateFormat.format(new Date());
            preparedStatement.setString(3, currentDate);

            // Set the status (1 for true, 0 for false)
            preparedStatement.setInt(4, 1);

            // Execute the update
            preparedStatement.executeUpdate();
            System.out.println("Exam added to the database.");

            return true;
        }
    }

public static boolean insertGradeSheetToDatabase(Connection connection, int facultyID, String path) throws SQLException {
    String insertQuery = "INSERT INTO grade_sheet (facultyID, path, date, status) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
        // Set parameters for the SQL query
        preparedStatement.setInt(1, facultyID);
        preparedStatement.setString(2, path);
        
        // Set the current date in MM/DD/YYYY format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = dateFormat.format(new Date());
        preparedStatement.setString(3, currentDate);

        // Set the status (1 for true, 0 for false)
        preparedStatement.setInt(4, 1);

        // Execute the update
        preparedStatement.executeUpdate();
        System.out.println("Grade Sheet added to the database.");

        return true;
    }
}

public static boolean insertItemAnalysisToDatabase(Connection connection, int facultyID, String path) throws SQLException {
    String insertQuery = "INSERT INTO grade_sheet (facultyID, path, date, status) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
        // Set parameters for the SQL query
        preparedStatement.setInt(1, facultyID);
        preparedStatement.setString(2, path);
        
        // Set the current date in MM/DD/YYYY format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = dateFormat.format(new Date());
        preparedStatement.setString(3, currentDate);

        // Set the status (1 for true, 0 for false)
        preparedStatement.setInt(4, 1);

        // Execute the update
        preparedStatement.executeUpdate();
        System.out.println("Item Analysis added to the database.");

        return true;
    }
}

public static boolean insertSyllabusToDatabase(Connection connection, int facultyID, String path) throws SQLException {
    String insertQuery = "INSERT INTO grade_sheet (facultyID, path, date, status) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
        // Set parameters for the SQL query
        preparedStatement.setInt(1, facultyID);
        preparedStatement.setString(2, path);
        
        // Set the current date in MM/DD/YYYY format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = dateFormat.format(new Date());
        preparedStatement.setString(3, currentDate);

        // Set the status (1 for true, 0 for false)
        preparedStatement.setInt(4, 1);

        // Execute the update
        preparedStatement.executeUpdate();
        System.out.println("Grade Sheet added to the database.");

        return true;
    }
}

public static boolean insertLoadToDatabase(Connection connection, int facultyID, String path) throws SQLException {
    String insertQuery = "INSERT INTO grade_sheet (facultyID, path, date, status) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
        // Set parameters for the SQL query
        preparedStatement.setInt(1, facultyID);
        preparedStatement.setString(2, path);
        
        // Set the current date in MM/DD/YYYY format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = dateFormat.format(new Date());
        preparedStatement.setString(3, currentDate);

        // Set the status (1 for true, 0 for false)
        preparedStatement.setInt(4, 1);

        // Execute the update
        preparedStatement.executeUpdate();
        System.out.println("Teaching Load added to the database.");

        return true;
    }
}

public static boolean insertSpecsToDatabase(Connection connection, int facultyID, String path) throws SQLException {
    String insertQuery = "INSERT INTO grade_sheet (facultyID, path, date, status) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
        // Set parameters for the SQL query
        preparedStatement.setInt(1, facultyID);
        preparedStatement.setString(2, path);
        
        // Set the current date in MM/DD/YYYY format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = dateFormat.format(new Date());
        preparedStatement.setString(3, currentDate);

        // Set the status (1 for true, 0 for false)
        preparedStatement.setInt(4, 1);

        // Execute the update
        preparedStatement.executeUpdate();
        System.out.println("Class record added to the database.");

        return true;
    }
}


    public static boolean insertfilesPopulator(Connection connection, int facultyID, String path) throws SQLException {
        String insertQuery = "INSERT INTO filesPopulator (facultyID, path, date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            // Set parameters for the SQL query
            preparedStatement.setInt(1, facultyID);
            preparedStatement.setString(2, path);
            
            // Set the current date in MM/DD/YYYY format
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String currentDate = dateFormat.format(new Date());
            preparedStatement.setString(3, currentDate);

            // Set the status (1 for true, 0 for false)
            preparedStatement.setInt(4, 1);

            // Execute the update
            preparedStatement.executeUpdate();
            System.out.println("Class record added to the database.");

            return true;
        }
    }

    // Get date submitted from the database in 
    public static String getfilesDate(File directory) {
        String query = "SELECT date FROM filesPopulator WHERE path = ?";
        String directoryString = directory.toString();
        String date = "";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, directoryString);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                date = resultSet.getString("date");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return date;
    }

    // Get year ID from the database based on academic year
    public static int getYearID(String academicYear) {
        String query = "SELECT yearID FROM year WHERE academicYear = ?";
        int yearID = -1;

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, academicYear);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                yearID = resultSet.getInt("yearID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return yearID;
    }

    // Get semester ID from the database based on semester name
    public static int getSemesterID(String semesterName) {
        String query = "SELECT semesterID FROM semester WHERE semesterName = ?";
        int semesterID = -1;

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, semesterName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                semesterID = resultSet.getInt("semesterID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return semesterID;
    }

    // Get faculty names from the database
    public static List<String> getFacultyNames() {
        List<String> facultyNames = new ArrayList<>();
        String query = "SELECT name FROM faculty";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                facultyNames.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facultyNames;
    }

    // Get department name from the database based on department ID
    public static String getDepartmentName(int departmentID) {
        String query = "SELECT departmentName FROM department WHERE deptID = ?";
        String departmentName = "";

        try (Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, departmentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                departmentName = resultSet.getString("departmentName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departmentName;
    }

    // Get academic year from the database based on year ID
    public static String getAcademicYear(int yearID) {
        String query = "SELECT academicYear FROM year WHERE yearID = ?";
        String academicYear = "";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, yearID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                academicYear = resultSet.getString("academicYear");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return academicYear;
    }

        public static String getAcademicYearOfFaculty(int facultyID) {
        String query = "SELECT yearID FROM faculty WHERE facultyID = ?";
        String academicYear = "";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, facultyID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                academicYear = resultSet.getString("yearID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return academicYear;
    }

    public static String getDepartmentOfFaculty(int facultyID) {
        String query = "SELECT deptID FROM faculty WHERE facultyID = ?";
        String department = "";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, facultyID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                department = resultSet.getString("deptID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return department;
    }

    public static String getFullNameOfFaculty(int facultyID) {
        String query = "SELECT name FROM faculty WHERE facultyID = ?";
        String name = "";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, facultyID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }

    public static String getSemOfFaculty(int facultyID) {
        String query = "SELECT semesterID FROM faculty WHERE facultyID = ?";
        String sem = "";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, facultyID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sem = resultSet.getString("semesterID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sem;
    }

    // Get semester name from the database based on semester ID
    public static String getYearName(int yearID) {
        String query = "SELECT academicYear FROM year WHERE yearID = ?";
        String yearName = "";
    
        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, yearID);
    
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                yearName = resultSet.getString("academicYear");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return yearName;
    }

    // Get semester name from the database based on semester ID
    public static String getSemesterName(int semesterID) {
        String query = "SELECT semesterName FROM semester WHERE semesterID = ?";
        String semesterName = "";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, semesterID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                semesterName = resultSet.getString("semesterName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return semesterName;
    }

    // Get a list of FacultyData objects along with related information
    public static List<FacultyData> getFacultyDataList() {
        List<FacultyData> facultyList = new ArrayList<>();

        String query = "SELECT facultyID, name, deptID, yearID, semesterID FROM faculty";
        try (Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int facultyID = resultSet.getInt("facultyID");
                String facultyName = resultSet.getString("name");
                int departmentID = resultSet.getInt("deptID");
                int yearID = resultSet.getInt("yearID");
                int semesterID = resultSet.getInt("semesterID");

                // Fetch department name, academic year, and semester name using existing methods
                String departmentName = getDepartmentName(departmentID);
                String academicYear = getYearName(yearID);
                String semesterName = getSemesterName(semesterID);

                // Create FacultyData object with complete information
                FacultyData facultyData = new FacultyData(facultyID, facultyName, departmentName, academicYear, semesterName);
                facultyList.add(facultyData);
            }
            System.out.println(facultyList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facultyList;
    }

    // Add this method to your DatabaseHandler class
    public static boolean deleteFacultyByID(int facultyID) {
        String query = "DELETE FROM faculty WHERE facultyID = ?";
        String query2 = "DELETE FROM fID_lastName WHERE facultyID = ?";
        
        try (Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query); 
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2)) {
            preparedStatement.setInt(1, facultyID);
            preparedStatement2.setInt(1, facultyID);
            
            int rowsDeleted = preparedStatement.executeUpdate() + preparedStatement2.executeUpdate();
            
            return rowsDeleted > 1;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return false;
        }
    }

    // Update semester in the database based on facultyID
    public static boolean updateSemester(String semester, int facultyID) {
        String query = "UPDATE faculty SET semesterID = (SELECT semesterID FROM semester WHERE semesterName = ?) WHERE facultyID = ?";
        
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, semester);
            preparedStatement.setInt(2, facultyID);

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return false;
        }
    }

    // Update academic year in the database based on facultyID
    public static boolean updateAcademicYear(String academicYear, int facultyID) {
        String query = "UPDATE faculty SET yearID = (SELECT yearID FROM year WHERE academicYear = ?) WHERE facultyID = ?";
        
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, academicYear);
            preparedStatement.setInt(2, facultyID);

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return false;
        }
    }

    public static int getFacultyID(String facultyName) {
        String query = "SELECT facultyID FROM faculty WHERE name = ?";
        int facultyID = -1;

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, facultyName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                facultyID = resultSet.getInt("facultyID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facultyID;
    }

    public static FacultyData getFacultyData(int facultyID) {
        FacultyData facultyData = null;

        String query = "SELECT name, deptID, yearID, semesterID FROM faculty WHERE facultyID = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, facultyID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String facultyName = resultSet.getString("name");
                int departmentID = resultSet.getInt("deptID");
                int yearID = resultSet.getInt("yearID");
                int semesterID = resultSet.getInt("semesterID");

                // Fetch department name, academic year, and semester name using existing methods
                String departmentName = getDepartmentName(departmentID);
                String academicYear = getAcademicYear(yearID);
                String semesterName = getSemesterName(semesterID);

                // Create FacultyData object with complete information
                facultyData = new FacultyData(facultyID, facultyName, departmentName, academicYear, semesterName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facultyData;
    }

    public static int insertSubject(String subjectName) {
        String query = "INSERT INTO subjects (subject) VALUES (?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, subjectName);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the generated subjectID
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int subjectID = generatedKeys.getInt(1);

                    return subjectID;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if the insertion fails
    }

    // Get the latest subject data from the database
    public static SubjectData  getLatestSubject() {
        String query = "SELECT * FROM subjects ORDER BY subjectID DESC LIMIT 1";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int subjectID = resultSet.getInt("subjectID");
                String subjectName = resultSet.getString("subject");
                String syllabus = resultSet.getString("syllabus");

                return new SubjectData(subjectID, subjectName, syllabus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

        public static void insertTeachingLoad(int facultyID, String path, int status) {
        String insertTeachingLoadQuery = "INSERT INTO teaching_load (facultyID, path, date, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(insertTeachingLoadQuery)) {

            preparedStatement.setInt(1, facultyID);
            preparedStatement.setString(2, path);
            preparedStatement.setString(3, null);
            preparedStatement.setInt(4, 0);
            

            preparedStatement.executeUpdate();
            System.out.println("Teaching load entry added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to insert teaching load entry.");
        }
    }

    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public static String getSubjectName(int subjectID) {
        String query = "SELECT subject FROM subjects WHERE subjectID = ?";
        String subjectName = "";

        try (Connection connection = connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, subjectID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subjectName = resultSet.getString("subject");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjectName;
    }

    public static List<SubjectData> getAllSubjects() {
        List<SubjectData> subjects = new ArrayList<>();
        String query = "SELECT * FROM subjects";
    
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                int subjectID = resultSet.getInt("subjectID");
                String subjectName = resultSet.getString("subject");
                String syllabus = resultSet.getString("syllabus");
    
                // Create a SubjectData object with the retrieved data
                SubjectData subjectData = new SubjectData(subjectID, subjectName, syllabus);
                subjects.add(subjectData);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    
        return subjects;
    }

    public List<String> getSectionsByFacultyAndSubject(int facultyID, int subjectID) {
        List<String> sections = new ArrayList<>();

        try (Connection connection = connect()) {
            String sql = "SELECT s.section FROM faculty_subject_section fss " +
                         "JOIN section s ON fss.sectionID = s.sectionID " +
                         "WHERE fss.facultyID = ? AND fss.subjectID = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, facultyID);
                pstmt.setInt(2, subjectID);

                ResultSet resultSet = pstmt.executeQuery();
                while (resultSet.next()) {
                    sections.add(resultSet.getString("section"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sections;
    }

     // Associate a faculty with a subject
     public static void associateFacultyWithSubject(int facultyID, int subjectID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connect();
            // Check if the association already exists
            String checkSql = "SELECT * FROM faculty_subject WHERE facultyID = ? AND subjectID = ?";
            preparedStatement = connection.prepareStatement(checkSql);
            preparedStatement.setInt(1, facultyID);
            preparedStatement.setInt(2, subjectID);
            resultSet = preparedStatement.executeQuery();

            // If the association doesn't exist, insert it
            if (!resultSet.next()) {
                // SQL statement to insert a record into faculty_subject table
                String insertSql = "INSERT INTO faculty_subject (facultyID, subjectID) VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(insertSql);
                preparedStatement.setInt(1, facultyID);
                preparedStatement.setInt(2, subjectID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Close resources in the reverse order of their creation
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }

    public static int getSubjectIDByFaculty(String facultyName, String subjectName) {
        int subjectID = -1;
    
        String query = "SELECT fss.subjectID " +
                       "FROM faculty_subject_section fss " +
                       "JOIN subjects s ON fss.subjectID = s.subjectID " +
                       "WHERE fss.facultyName = ? AND s.subject = ?";
        
        try (Connection connection = DatabaseHandler.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, facultyName);
            preparedStatement.setString(2, subjectName);
    
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subjectID = resultSet.getInt("subjectID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectID;
    }

    public static int getSubjectID(String subjectDisplay) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connect();
            String query = "SELECT subjectID FROM subjects WHERE subject = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, subjectDisplay);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Subject exists, return the subjectID
                return resultSet.getInt("subjectID");
            } else {
                // Subject doesn't exist
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's requirements
            return -1;
        } finally {
            // Close the resources (result set, statement, connection) in the reverse order of their creation
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public static List<SubjectData> getSubjectsByFaculty(int facultyID) {
        List<SubjectData> subjects = new ArrayList<>();
        Connection connection = null;

        try {
            connection = connect();
            // Assuming you have a table named "FacultySubjects" that associates faculty and subjects
            String query = "SELECT subjects.* FROM faculty_subject " +
                           "JOIN subjects ON faculty_subject.subjectID = subjects.subjectID " +
                           "WHERE faculty_subject.facultyID = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, facultyID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int subjectID = resultSet.getInt("subjectID");
                        String subjectName = resultSet.getString("subject");
                        String syllabus = resultSet.getString("syllabus");

                        // You may need to adjust the SubjectData constructor based on your SubjectData class
                        SubjectData subjectData = new SubjectData(subjectID, subjectName, syllabus);
                        subjects.add(subjectData);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception based on your application's requirements
        }

        return subjects;
    }

    public static int addSection(String sectionName) {
        int sectionID = -1;
    
        String insertQuery = "INSERT INTO section (section) VALUES (?)";
        String selectQuery = "SELECT sectionID FROM section WHERE section = ?";
    
        try (Connection connection = connect();
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
    
            // Insert section into the sections table
            insertStatement.setString(1, sectionName);
            int rowsAffected = insertStatement.executeUpdate();
    
            if (rowsAffected > 0) {
                // Retrieve the generated section ID
                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    sectionID = generatedKeys.getInt(1);
                }
    
                // Optionally, you can also select the section ID to ensure accuracy
                selectStatement.setString(1, sectionName);
                ResultSet resultSet = selectStatement.executeQuery();
                if (resultSet.next()) {
                    sectionID = resultSet.getInt("sectionID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return sectionID;
    }
    
    public static List<String> getSections(int facultyID, int subjectID) {
        List<String> sectionsList = new ArrayList<>();

        try {
            Connection connection = connect();
            String query = "SELECT section FROM faculty_subject_section " +
                           "WHERE facultyID = ? AND subjectID = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, facultyID);
                preparedStatement.setInt(2, subjectID);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String sectionName = resultSet.getString("section");
                    sectionsList.add(sectionName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or log it
        }

        return sectionsList;
    }


    public static void associateSectionWithFacultySubject(int facultyID, int subjectID, int sectionID) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:main.db")) {
            String query = "INSERT INTO faculty_subject_section (facultyID, subjectID, sectionID) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, facultyID);
                preparedStatement.setInt(2, subjectID);
                preparedStatement.setInt(3, sectionID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or log it
        }
    }

    public static void deleteFacultySubjectSection(int facultyID, int subjectID, int sectionID) {
        String query = "DELETE FROM faculty_subject_section WHERE facultyID = ? AND subjectID = ? AND sectionID = ?";
    
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, facultyID);
            preparedStatement.setInt(2, subjectID);
            preparedStatement.setInt(3, sectionID);
    
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFacultySubjectSectionBySectionID(int sectionID) {
        String query = "DELETE FROM faculty_subject_section WHERE sectionID = ?";
    
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sectionID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFacultySubjectSectionBySubjectID(int subjectID) {
        String query = "DELETE FROM faculty_subject_section WHERE subjectID = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, subjectID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateSubjectName(int subjectID, String newSubjectName) {
        String query = "UPDATE subjects SET subject = ? WHERE subjectID = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newSubjectName);
            preparedStatement.setInt(2, subjectID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSubjectByID(int subjectID) {
        String query = "DELETE FROM subjects WHERE subjectID = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, subjectID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteSection(int sectionID) {
        String query = "DELETE FROM section WHERE sectionID = ?";
    
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sectionID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertFLnID(int facultyID, String lastName) {
        String query = "INSERT INTO fID_lastName (facultyID, lastName) VALUES (?, ?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, facultyID);
            preparedStatement.setString(2, lastName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getLastNameByFacultyID(int facultyID) {
        String lastName = null;

        String query = "SELECT lastName FROM fID_lastName WHERE facultyID = ?";

        try (Connection connection = connect();
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

    public static int getFacultyIDByLastName(String lastName) {
        int facultyID = -1;
        
        String query = "SELECT facultyID FROM fID_lastName WHERE lastName = ?;";
    
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    
            preparedStatement.setString(1, lastName);
    
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                facultyID = resultSet.getInt("facultyID");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return facultyID;
    }

    public static int getSectionID(String sectionName) {
        int sectionID = -1;
    
        String query = "SELECT sectionID FROM section WHERE section = ?";
        
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, sectionName);
    
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sectionID = resultSet.getInt("sectionID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionID;
    }
    
    // Helper methods for closing resources
    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
