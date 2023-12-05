import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facultyList;
    }

    // Add this method to your DatabaseHandler class
    public static boolean deleteFacultyByID(int facultyID) {
        String query = "DELETE FROM faculty WHERE facultyID = ?";
        
        try (Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, facultyID);
            
            int rowsDeleted = preparedStatement.executeUpdate();
            
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return false;
        }
    }


}
