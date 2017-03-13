package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import config.Configuration;

public class DBManager {

    static DBManager instance;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    String url;
    String user;
    String password;
    Connection connection;

    private DBManager() throws SQLException {
        ResourceBundle rb = Configuration.getInstance().getBundle("config");
        url = rb.getString("database_url");
        user = rb.getString("database_user");
        password = rb.getString("database_password");
        connection = DriverManager.getConnection(url, user, password);
    }

    public static DBManager getInstance() throws SQLException {
        if (instance == null) instance = new DBManager();
        return instance;
    }

    public boolean close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Connection getConnection() {
        return connection;
    }
}
