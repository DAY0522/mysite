package mysite.repository;

import mysite.vo.GuestbookVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GuestbookRepository {
    private SqlSession sqlSession;

    public GuestbookRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<GuestbookVo> findAll() {
        return sqlSession.selectList("guestbook.findAll");
    }

    public GuestbookVo findById(Long id) {
        return sqlSession.selectOne("guestbook.findById", id);
    }

    public int insert(GuestbookVo vo) {
        return sqlSession.insert("guestbook.insert", vo);
    }

    public int deleteByIdAndPassword(Long id, String password) {
        Map<String, Object> map = Map.of("id", id, "password", password); // vo를 굳이 안 만들고 map을 넣어줘도 됨.
        return sqlSession.delete("guestbook.deleteByIdAndPassword", map);
    }


}
