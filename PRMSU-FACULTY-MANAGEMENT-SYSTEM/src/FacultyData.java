import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyData {
    private String facultyName;
    private String departmentName;
    private String academicYear;
    private String semesterName;
    private int facultyID;

    public FacultyData(int facultyID, String facultyName, String departmentName, String academicYear, String semesterName) {
        this.facultyID = facultyID;
        this.facultyName = facultyName;
        this.departmentName = departmentName;
        this.academicYear = academicYear;
        this.semesterName = semesterName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public String getDepartmentName() {
        int deptID = getDepartmentID();
        String departmentName= DatabaseHandler.getDepartmentName(deptID);
        return departmentName;
    }

    public String getAcademicYear() {
        int yearID = getYearID();
        String academicYear = DatabaseHandler.getAcademicYear(yearID);
        return academicYear;
    }

    public String getSemesterName() {
        int semesterID = getSemesterID();
        String semesterName = DatabaseHandler.getSemesterName(semesterID);
        return semesterName;
    }

    public int getDepartmentID() {
        int departmentID = -1;

        String query = "SELECT deptID FROM department WHERE departmentName = ?";
        
        try (Connection connection = DatabaseHandler.connect();
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

    public int getYearID() {
        int yearID = -1;

        String query = "SELECT yearID FROM year WHERE academicYear = ?";
        
        try (Connection connection = DatabaseHandler.connect();
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

    public int getSemesterID() {
        int semesterID = -1;

        String query = "SELECT semesterID FROM semester WHERE semesterName = ?";
        
        try (Connection connection = DatabaseHandler.connect();
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

    public int getOriginFacultyID() {
       int facultyID = -1;

        String query = "SELECT facultyID FROM faculty WHERE facultyID = ?";
        
        try (Connection connection = DatabaseHandler.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                facultyID = resultSet.getInt("facultyID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facultyID;
    }
    
    public String getLastNameByFacultyID() {
        String lastName = null;

        String query = "SELECT lastName FROM fID_lastName";

        try (Connection connection = DatabaseHandler.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lastName = resultSet.getString("lastName");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastName;
    }

        public static int getFacultyID() {
       int facultyID = -1;

        String query = "SELECT facultyID FROM faculty WHERE name = ?";
        
        try (Connection connection = DatabaseHandler.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1, facultyID);
                System.out.println("Connected to  dtabase,,, fetching dta");

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                facultyID = resultSet.getInt("facultyID");
                System.out.println(facultyID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facultyID;
    }
}