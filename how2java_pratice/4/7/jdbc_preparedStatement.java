package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
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
        String sql = "insert into hero values(null,?,?,?)";
        try (
                Connection c = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                        username,
                        password
                );
                Statement s = c.createStatement();
                PreparedStatement ps = c.prepareStatement(sql);
        ){
            long startTime = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                s.execute("insert into hero values(null," + "'hero" + i+"'" + "," + 313.0f + "," + 50 + ")");
            }
            long stopTime = System.nanoTime();
            System.out.println(stopTime - startTime);
            startTime = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                ps.setString(1, "hero"+(20000+i));
                ps.setFloat(2, 313.0f);
                ps.setInt(3, 50);
                ps.execute();
            }
            stopTime = System.nanoTime();
            System.out.println(stopTime - startTime);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
