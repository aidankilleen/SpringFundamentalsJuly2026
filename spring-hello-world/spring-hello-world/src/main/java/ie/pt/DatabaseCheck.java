package ie.pt;

import java.sql.*;

public class DatabaseCheck {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:sqlite:C:\\work\\training\\java\\users.db";
        Connection conn = DriverManager.getConnection(url);

        String sql = "SELECT * FROM users";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            System.out.println(name);
        }

        rs.close();
        stmt.close();
        conn.close();
    }
}
