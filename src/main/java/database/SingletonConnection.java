package database;

import exceptions.logger.CostumeLogger;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingletonConnection {
    private static final String URL = System.getenv("SERVER");
    private static final String USER = System.getenv("USER");
    private static final String PASSWORD = System.getenv("PASSWORD");
    private static final Logger logger = Logger.getLogger(SingletonConnection.class.getName());
    private static SingletonConnection dbConn;
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
    public static void closeAll(Closeable ... chanels){
        for(Closeable closeIt:chanels){
            try{
                if(closeIt!=null)closeIt.close();
            }catch (IOException e){
                CostumeLogger.getInstance().logError(e);
            }
        }

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

    public Connection getConnection() {
        return this.conn;
    }
}
