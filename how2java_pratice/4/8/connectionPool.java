package connectionpool;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by macbookpro on 2020/4/8.
 */
public class ConnectionPool {

    private int size;
    private List<Connection> connections;

    public ConnectionPool(int size) {
        this.size = size;
        connections = new LinkedList<>();
        Properties prop = new Properties();
        try (FileInputStream inputStream = new FileInputStream("resource/config.properties")) {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < size; i++) {
                Connection c = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                        username,
                        password
                );
                connections.add(c);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized Connection getConnection() {
        while (connections.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Connection c = connections.remove(0);
        return c;
    }

    public synchronized void returnConnection(Connection c) {
        connections.add(c);
        this.notifyAll();
    }
}
