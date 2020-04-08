package connectionpool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by macbookpro on 2020/4/8.
 */
public class TestConnectionPool {

    public static void main(String[] args) {
        ConnectionPool cp = new ConnectionPool(10);
        long startTime;
        long stopTime;
        startTime = System.nanoTime();
        insertWithoutConnectionPool();
        stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
        startTime = System.nanoTime();
        insertWithConnectionPool(cp);
        stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);

    }

    public static void insertWithConnectionPool(ConnectionPool cp) {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            final int index = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    Connection c = cp.getConnection();
//                    while (c == null) {
//                        try {
//                            cp.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        c = cp.getConnection();
//                    }
                    String sql = "insert into hero values(null,?,?,?)";
                    try (
                            PreparedStatement ps = c.prepareStatement(sql);
                    ) {
                        ps.setString(1, "a"+index);
                        ps.setFloat(2, 567);
                        ps.setInt(3, 123);
                        ps.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    cp.returnConnection(c);
                }
            });
            t.start();
            threads[i] = t;
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertWithoutConnectionPool() {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            final int index = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    String sql = "insert into hero values(null,?,?,?)";
                    try (
                            Connection c = DriverManager.getConnection(
                                    "jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                                    "root",
                                    "Spx19910628"
                            );
                            PreparedStatement ps = c.prepareStatement(sql);
                    ) {
                        ps.setString(1, "a"+index);
                        ps.setFloat(2, 567);
                        ps.setInt(3, 123);
                        ps.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            threads[i] = t;
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
