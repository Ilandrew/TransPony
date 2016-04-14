package by.bsuir.mpp.transpony.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

    private static DatabaseUtils instance;
    private final DataSource dataSource;

    public DatabaseUtils() {
        dataSource = null;
    }

    private DatabaseUtils(int a) throws NamingException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("java:comp/env");

        String dataSourceName = "jdbc/transPony";
        dataSource = (DataSource) context.lookup(dataSourceName);
    }


    public static DatabaseUtils getInstance() throws NamingException {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    private synchronized static void createInstance() throws NamingException {
        if (instance == null) {
            instance = new DatabaseUtils(1);
        }
    }

    public static void setInstance(DatabaseUtils instance) {
        DatabaseUtils.instance = instance;
    }

    public Connection getConnection() throws NamingException, SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Can't close connection!");
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Can't close statement!");
            }
        }
    }

    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                System.out.println("Can't rollback connection!");
            }
        }
    }
//
//    public static void commit() {
//        if (transactionOn) {
//            try {
//                connection.commit();
//                connection.setAutoCommit(true);
//                transactionOn = false;
//            } catch (SQLException e) {
//                System.out.println("Can't commit");
//            }
//        }
//    }
}
