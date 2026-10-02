package secure_signup;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BBDD {
    private static String bbdd=DatabaseConfig.getUrl();
    private static Connection connection;

    public static void connection() throws SQLException {
        connection= DriverManager.getConnection(bbdd,DatabaseConfig.getUser(),DatabaseConfig.getPassword());
    }
    public static void disconnection() throws SQLException {
        connection.close();
    }


    public static boolean addUsers(String name, String lastName, String email, String passwordHash) throws SQLException {
        connection();
        String sql="INSERT INTO users(name_,last_name,email,password_) VALUES(?,?,?,?)";
        PreparedStatement sentence=connection.prepareStatement(sql);
        sentence.setString(1,name);
        sentence.setString(2,lastName);
        sentence.setString(3,email);
        sentence.setString(4,passwordHash);
        int insertRows=sentence.executeUpdate();
        disconnection();
        if (insertRows==0) return false;
        else return true;

    }
}
