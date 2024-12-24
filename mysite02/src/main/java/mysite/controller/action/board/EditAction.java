package mysite.controller.action.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.controller.ActionServlet;
import mysite.dao.BoardDao;
import mysite.vo.BoardVo;
import mysite.vo.UserVo;

import java.io.IOException;
import java.util.Optional;

public class EditAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Optional<Long> boardId = Optional.of(Long.parseLong(request.getParameter("id"))); // Optional.ofNullable(request.getParameter("id")
        Long id = boardId.orElseThrow(() -> new IllegalArgumentException("id 값이 없습니다."));
        BoardVo boardVo = new BoardDao().findById(id);

        HttpSession session = request.getSession();
        if (session != null) {
            UserVo authUser = (UserVo) session.getAttribute("authUser");
            if (authUser != null) {
                Long authUserId = authUser.getId();
                Long boardUserId = boardVo.getUserId();
                if (authUserId.equals(boardUserId)) { // 본인이 쓴 글인 경우
                    // 수정하기
                    BoardVo vo = new BoardVo();
                    vo.setId(id);
                    vo.setTitle(request.getParameter("title"));
                    vo.setContents(request.getParameter("contents"));
                    new BoardDao().update(vo);
                    response.sendRedirect(request.getContextPath() + "/board?a=view&id=" + id);
                    return;
                } else { // 본인이 쓴 글이 아닌 경우
                    response.sendRedirect(request.getContextPath());
                    return;
                }
            } else { // 현재 유저 세션이 없는 경우
                response.sendRedirect(request.getContextPath());
                return;
            }
        } else { // 비회원인 경우
            response.sendRedirect(request.getContextPath());
            return;
        }
    }
}
