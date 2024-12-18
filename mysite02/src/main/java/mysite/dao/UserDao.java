package mysite.dao;

import mysite.vo.GuestbookVo;
import mysite.vo.UserVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public int insert(UserVo vo) {
        int count = 0;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("insert into user values(null, ?, ?, ?, ?, CURDATE())");
        ) {

            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getEmail());
            pstmt.setString(3, vo.getPassword());
            pstmt.setString(4, vo.getGender());

            System.out.println("name: " + vo.getName());
            System.out.println("email: " + vo.getEmail());
            System.out.println("password: " + vo.getPassword());
            System.out.println("gender: " + vo.getGender());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        return count;
    }

    private Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2/webdb";
            conn = DriverManager.getConnection(url, "webdb", "webdb");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e);
        }

        return conn;
    }
}
