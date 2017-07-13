package ro.teamnet.zth.api.database;

import java.sql.*;

/**
 * Created by Emilia.Palaghita on 13-Jul-17.
 */
public class DBManager {
    private static final String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":xe";
    Driver driver;

    private DBManager() {
        throw new UnsupportedOperationException();
    }

    private static void registerDriver() {
        try {
            Class.forName(DBProperties.DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        registerDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, DBProperties.USER, DBProperties.PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static ResultSet checkConnection(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT 1 FROM DUAL");
            statement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
