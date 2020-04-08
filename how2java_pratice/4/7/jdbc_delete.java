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
        String createSql = "insert into hero values(null,?,?,?)";
        String deleteSql = "delete from hero where id = ?";
        try (
                Connection c = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                        username,
                        password
                );
                PreparedStatement createStatement = c.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement deleteStatement = c.prepareStatement(deleteSql);
        ){
            createStatement.setString(1, "timo");
            createStatement.setFloat(2, 432);
            createStatement.setInt(3, 120);
            createStatement.execute();
            ResultSet rs = createStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey= rs.getInt(1);
            }
            System.out.println(generatedKey);
            while (true) {
                generatedKey -= 1;
                deleteStatement.setInt(1, generatedKey);
                int result = deleteStatement.executeUpdate();
                System.out.println(result);
                if (result != 0) break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
