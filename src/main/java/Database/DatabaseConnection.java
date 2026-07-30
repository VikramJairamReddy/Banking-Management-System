/**
 * This class handles the connection between the Java application and MySQL database.
 *
 * Responsibilities:
 * - Store database connection information.
 * - Create and return database connections.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // MySQL database URL which contains server location, port, and database name.
    private static final String URL = "jdbc:mysql://localhost:3306/BankingSystem";

    // MySQL username for authentication.
    private static final String USER = "root";

    // MySQL password for authentication.
    private static final String PASSWORD = "";


    /**
     * Creates a connection with the MySQL database.
     *
     * @return active database connection if successful, otherwise returns null.
     */
    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch(SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
            return null;
        }
    }
}