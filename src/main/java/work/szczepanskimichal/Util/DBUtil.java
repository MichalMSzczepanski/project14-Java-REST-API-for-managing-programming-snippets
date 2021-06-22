package work.szczepanskimichal.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    final static String DBname = "snippetAPIDB";
    final static String user = "root";
    final static String password = "coderslab";
    final static String URL = "jdbc:mysql://localhost:3306/" + DBname + "?serverTimezone=UTC";

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(URL, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

}
