package mysite.dao;

import mysite.vo.BoardVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
    public static final Integer PAGE_SIZE = 5;

    public Integer findLastGId() {
        Integer result = 0;
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select max(g_no) from board");
        ) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
        return result;
    }

    public List<BoardVo> findAll(Integer page) {
        List<BoardVo> result = new ArrayList<>();

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select b.id, " +
                        "title, contents, hit, date_format(reg_date, '%Y-%m-%d %h:%i:%s'), " +
                        "g_no, o_no, depth, user_id, name " +
                        "from board b left outer join user u " +
                        "on b.user_id = u.id " +
                        "order by g_no desc, o_no asc limit ?, ?");
        ) {
            // limit
            pstmt.setInt(1, (page - 1) * PAGE_SIZE);
            pstmt.setInt(2, PAGE_SIZE);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong(1);
                String title = rs.getString(2);
                String contents = rs.getString(3);
                Integer hit = rs.getInt(4);
                String regDate = rs.getString(5);
                Integer g_no = rs.getInt(6);
                Integer o_no = rs.getInt(7);
                Integer depth = rs.getInt(8);
                Long userId = rs.getLong(9);
                String userName = rs.getString(10);

                BoardVo vo = new BoardVo();
                vo.setId(id);
                vo.setTitle(title);
                vo.setContents(contents);
                vo.setHit(hit);
                vo.setRegDate(regDate);
                vo.setG_no(g_no);
                vo.setO_no(o_no);
                vo.setDepth(depth);
                vo.setUserId(userId);
                vo.setUserName(userName);

                result.add(vo);
            }
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        return result;
    }

    public int insert(BoardVo vo) {
        int count = 0;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("insert into board values(null, ?, ?, ?, now(), ifnull(?, 1), ?, ?, ?)");
        ) {

            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContents());
            pstmt.setInt(3, vo.getHit());
            pstmt.setInt(4, vo.getG_no());
            pstmt.setInt(5, vo.getO_no());
            pstmt.setInt(6, vo.getDepth());
            pstmt.setLong(7, vo.getUserId());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
        return count;
    }

    public BoardVo findById(Long id) {
        BoardVo vo = null;
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select id, title, contents, user_id, hit, g_no, o_no, depth from board where id = ?");
        ) {
            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Long i = rs.getLong(1);
                String title = rs.getString(2);
                String contents = rs.getString(3);
                Long userId = rs.getLong(4);
                Integer hit = rs.getInt(5);
                Integer g_no = rs.getInt(6);
                Integer o_no = rs.getInt(7);
                Integer depth = rs.getInt(8);

                vo = new BoardVo();
                vo.setId(i);
                vo.setTitle(title);
                vo.setContents(contents);
                vo.setUserId(userId);
                vo.setHit(hit);
                vo.setG_no(g_no);
                vo.setO_no(o_no);
                vo.setDepth(depth);
            }

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        return vo;
    }

    public Integer findEndPage() {
        Double count = null;
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select count(*) from board"); // 모든 게시글 개수 구하기
        ) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1) / (double) PAGE_SIZE;
            }
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        return (int) Math.ceil(count);
    }

    public int deleteById(Long id) {
        int count = 0;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("delete from board where id = ?");
        ) {
            pstmt.setLong(1, id);
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

    public void update(BoardVo vo) {
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("update board set title = ?, contents = ? where id = ?");
        ) {
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContents());
            pstmt.setLong(3, vo.getId());

            pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
    }

    public void updateHitCount(BoardVo vo) {
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("update board set hit = ? where id = ?");
        ) {
            pstmt.setInt(1, vo.getHit() + 1);
            pstmt.setLong(2, vo.getId());

            pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
    }

    public void updateGroupOrder(BoardVo vo) {
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("update board set o_no = o_no+1 where g_no = ? and o_no >= ?");
        ) {
            pstmt.setInt(1, vo.getG_no());
            pstmt.setLong(2, vo.getO_no());

            pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
    }

    public Integer findCountAll() {
        Integer count = null;
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select count(*) from board");
        ) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        return count;
    }
}
