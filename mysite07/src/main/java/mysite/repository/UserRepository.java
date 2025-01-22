package mysite.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import mysite.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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

    public <R> R findByEmail(String email, Class<R> resultType) {
        Map<String, Object> map = sqlSession.selectOne("user.findByEmail", email);
        return new ObjectMapper().convertValue(map, resultType);
        // Jackson 라이브러리에서 제공하는 클래스. Java 객체와 JSON 간의 변환을 돕는 데 사용. 여기서는 Map을 지정된 타입(resultType)의 객체로 변환하는 데 사용되고 있습니다.
    }

    public int update(UserVo vo) {
        return sqlSession.update("user.update", vo);
    }
}
