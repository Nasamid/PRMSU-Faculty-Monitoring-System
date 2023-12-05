import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

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

    public static void main(String[] args) {
        // Example usage
        Connection connection = connect();
        if (connection != null) {
            System.out.println("Connected to the database!");
            // Perform database operations
            // Remember to close the connection when done
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database!");
        }
    }
}
