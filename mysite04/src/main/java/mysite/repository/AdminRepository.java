package mysite.repository;

import mysite.vo.AdminVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {
    private final SqlSession sqlSession;

    public AdminRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public AdminVo findContent() {
        return sqlSession.selectOne("admin.findContent");
    }

    public void update(AdminVo vo) {
        sqlSession.update("admin.update", vo);
    }
}
