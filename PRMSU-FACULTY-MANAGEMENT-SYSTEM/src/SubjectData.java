import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectData {

    private int subjectID;
    private String subjectName;
    private String syllabus;

    public SubjectData(int subjectID, String subjectName, String syllabus) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.syllabus = syllabus;
    }

    public int getSubjectID() {
        int subjectID = -1;

        String query = "SELECT subjectID FROM subjects WHERE subject = ?";
        
        try (Connection connection = DatabaseHandler.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, subjectName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subjectID = resultSet.getInt("subjectID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectID;
    }
        
    
    public String getSubjectName() {
        int subjectID = getSubjectID();
        String subjectName= DatabaseHandler.getSubjectName(subjectID);
        return subjectName;
    }

    public String getSyllabus() {
        return syllabus;
    }
}
