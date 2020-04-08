package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by macbookpro on 2020/4/7.
 */
public class testJDBC {
    public static void main(String[] args) {
        Properties prop = new Properties();
        try (FileInputStream inputStream = new FileInputStream("resource/config.properties")) {
            prop.load(inputStream);
            Class.forName("com.mysql.jdbc.Driver");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        try (
                Connection c = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                        username,
                        password
                );
                Statement s = c.createStatement();
        ){
            System.out.println(username + " " + password);
            for (int i = 0; i < 100; i++) {
                String sql = "insert into hero values(null," + "'提莫" + i + "'" + "," + 313.0f + "," + 50 + ")";
                s.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
