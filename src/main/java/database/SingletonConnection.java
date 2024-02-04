package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingletonConnection {
    private static SingletonConnection dbConn;
    private static final String URL = "jdbc:mysql://localhost:3306/gymbuddy";
    private static final String USER = "gymbuddy";
    private static final String PASSWORD = "root1234!S";

    private static final Logger logger = Logger.getLogger(SingletonConnection.class.getName());
    private Connection conn;

    private SingletonConnection() {
        try {
            conn = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD);
        } catch ( SQLException e) {
            this.conn = null;
        }

    }

    public Connection getConnection() {
        return this.conn;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error closing database connection", e);
            }
        }
    }

    public static synchronized SingletonConnection getInstance() {
        if (dbConn == null) {
            dbConn = new SingletonConnection();
        }
        return dbConn;
    }

    public static void deleteInstance() {
        dbConn = null;
    }
}
