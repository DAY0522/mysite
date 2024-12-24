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

import static mysite.controller.ActionServlet.*;

public class DeleteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long boardId = Long.parseLong(request.getParameter("id"));
        BoardVo boardVo = new BoardDao().findById(boardId);

        HttpSession session = request.getSession();
        if (session != null) { // 세션이 없는 경우
            UserVo authUser = (UserVo) session.getAttribute("authUser");
            if (authUser != null) { // authUser 쿠키가 없는 경우
                Long authUserId = authUser.getId();
                Long boardUserId = boardVo.getUserId();
                if (authUserId.equals(boardUserId)) { // 본인이 쓴 글인 경우
                    // 삭제하기
                    new BoardDao().deleteById(boardId);
                    response.sendRedirect(request.getContextPath() + "/board");
                    return;
                } else { // 본인이 쓴 글이 아닌 경우
                    response.sendRedirect(request.getContextPath() + "/board");
                    return;
                }
            } else { // 현재 유저 세션이 없는 경우
                response.sendRedirect(request.getContextPath() + "/board");
                return;
            }
        } else { // 비회원인 경우
            response.sendRedirect(request.getContextPath() + "/board");
            return;
        }
    }
}
