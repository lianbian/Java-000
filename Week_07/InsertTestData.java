package Week_07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

public class InsertTestData {
    private Connection connection = null;

    public static void main(String[] args) {
        InsertTestData insertTestData = new InsertTestData();
        insertTestData.createConnection();
        String sql = "insert into DemoOrder (OrderId, Money, ItemId, CreateTime) values (?, ?, ?, ?)";
        try {
            PreparedStatement pstm = insertTestData.connection.prepareStatement(sql);
            Long startTime = System.currentTimeMillis();
            Random random = new Random();
            for (int i = 1; i <= 1000000; i++) {
                pstm.setInt(1, random.nextInt(900000000));
                pstm.setInt(2, random.nextInt(100));
                pstm.setInt(3, random.nextInt(900000000));
                pstm.setLong(4, 111);
                pstm.addBatch();
            }
            pstm.executeBatch();
            Long endTime = System.currentTimeMillis();
            System.out.println("OK,用时：" + (endTime - startTime));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // pstm = insertTestData.connection.prepareStatement(sql);
    }

        private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println();
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find mysql jdbc driver");
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            if (connection != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
