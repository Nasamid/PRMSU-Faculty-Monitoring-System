import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class DatabaseSetup {

    // Define the path to the SQLite database file
    private static final String DB_PATH = "jdbc:sqlite:main.db";

    // SQLite JDBC driver class name
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    
    public static void main(String[] args) {
        FlatMacLightLaf.setup();
        // Load the SQLite JDBC driver
        loadDriver();

        // Create the documents table if it doesn't exist
        createDocumentsTable();
    }

    private static void loadDriver() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        }
    }

    private static void createDocumentsTable() {
        try (Connection connection = DriverManager.getConnection(DB_PATH);
             Statement statement = connection.createStatement()) {
            // Check if the 'documents' table already exists
            if (!tableExists(connection, "documents")) {
                // SQL statement to create the 'documents' table
                String sql = "CREATE TABLE documents (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "path TEXT," +
                        "upload_date TEXT" +
                        ")";
                // Execute the SQL statement to create the table
                statement.execute(sql);
                System.out.println("Table 'documents' created successfully.");
            } else {
                System.out.println("Table 'documents' already exists.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        // Check if the table already exists in the database
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName, null)) {
            return resultSet.next();
        }
    }
}
