package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:postgresql://localhost:5432/University";
        Connection dbConnection = DriverManager.getConnection(connectionString, "postgres", "root");
        return dbConnection;
    }
}
