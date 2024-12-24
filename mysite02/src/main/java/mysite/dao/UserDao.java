package mysite.dao;

import mysite.vo.UserVo;

import java.sql.*;

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

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        return count;
    }

    public UserVo findByEmailAndPassword(String email, String password) {
        UserVo vo = null;
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select id, name from user where email = ? and password = ?");
        ) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);

                vo = new UserVo();
                vo.setId(id);
                vo.setName(name);
            }

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        return vo;
    }

    public UserVo findById(Long id) {
        UserVo vo = null;
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select email, name, gender from user where id = ?");
        ) {
            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String email = rs.getString(1);
                String name = rs.getString(2);
                String gender = rs.getString(3);

                vo = new UserVo();
                vo.setEmail(email);
                vo.setName(name);
                vo.setGender(gender);
            }

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        return vo;
    }

    public void update(UserVo vo) {

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("update user set name = ?, gender = ?, password = ? where id = ?");
        ) {
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getGender());
            pstmt.setString(3, vo.getPassword());
            pstmt.setLong(4, vo.getId());

            pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
    }

    public void updateNoPassword(UserVo vo) {

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("update user set name = ?, gender = ? where id = ?");
        ) {
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getGender());
            pstmt.setLong(3, vo.getId());

            pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
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
