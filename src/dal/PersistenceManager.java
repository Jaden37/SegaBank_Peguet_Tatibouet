package dal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PersistenceManager {
    private static Connection connection;

    public static Connection getConnection() throws SQLException, IOException {
        if(connection == null || connection.isClosed() || connection.isValid(5)){
            Path path = Paths.get("./resources/db.properties");
            InputStream is = new FileInputStream(String.valueOf(path));
            Properties props = new Properties();
            props.load(is);
            try{
                connection = DriverManager.getConnection(props.getProperty("DB_URL"),props.getProperty("DB_LOGIN"),props.getProperty("DB_PASSWORD"));
                if (connection != null) {
                    System.out.println("Connected to the database");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if(connection != null){
            connection.close();
            System.out.println("Disconnected from the database");
        }
    }
}
