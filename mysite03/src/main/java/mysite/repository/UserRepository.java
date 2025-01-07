package mysite.repository;

import mysite.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import java.util.Map;

@Repository
public class UserRepository {

    private SqlSession sqlSession;

    public UserRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int insert(UserVo vo) {
        return sqlSession.insert("user.insert", vo);
    }

    public UserVo findByEmailAndPassword(String email, String password) {

        return sqlSession.selectOne("user.findByEmailAndPassword", Map.of("email", email, "password", password));
    }

    public UserVo findById(Long userId) {
        return sqlSession.selectOne("user.findById", userId);
    }

    public int update(UserVo vo) {
        return sqlSession.update("user.update", vo);
    }
}
