package mysite.repository;

import mysite.vo.BoardVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {

    private SqlSession sqlSession;

    public BoardRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    public static final Integer PAGE_SIZE = 5;

    public Integer findLastGId() {
        return sqlSession.selectOne("board.findLastGId");
    }

    public List<BoardVo> findByPageAndKeyword(int cuurentPage, String keyword) {
        Map<String, Object> map = Map.of("offset", (cuurentPage - 1) * PAGE_SIZE,
                "pageSize", PAGE_SIZE,
                "keyword", keyword.isBlank() ? "" : "%" + keyword + "%");
        return sqlSession.selectList("board.findByPageAndKeyword", map);
    }

    public int insert(BoardVo vo) {
        return sqlSession.insert("board.insert", vo);
    }

    public BoardVo findById(Long id) {
        return sqlSession.selectOne("board.findById", id);
    }

    public int deleteByIdAndUserId(Long id, Long userId) {
        Map<String, Object> map = Map.of("id", id, "userId", userId);
        return sqlSession.delete("board.deleteByIdAndUserId", map);
    }

    public void update(BoardVo vo) {
        Map<String, Object> map = Map.of("title", vo.getTitle(), "contents", vo.getContents(), "id", vo.getId());
        sqlSession.update("board.update", map);
    }

    public void updateHitCount(Long id) {
        sqlSession.update("board.updateHitCount", id);
    }

    public void updateGroupOrder(BoardVo vo) {
        Map<String, Object> map = Map.of("g_no", vo.getG_no(), "o_no", vo.getO_no());
        sqlSession.update("board.updateGroupOrder", map);
    }

    public Integer findCountAll(String keyword) {
        return sqlSession.selectOne("board.findCountAll", keyword);
    }
}
