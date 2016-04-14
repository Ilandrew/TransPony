package by.bsuir.mpp.transpony.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtilsTest extends DatabaseUtils {

    public DatabaseUtilsTest() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
        } catch (Exception e) {
            System.out.print("All bad:(");
        }
    }


    @Override
    public Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/transpony?characterEncoding=utf8&user=root&password=12345678";
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.print("All bad:(");
        }
        return null;
    }
}