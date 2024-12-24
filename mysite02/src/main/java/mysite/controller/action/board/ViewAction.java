package mysite.controller.action.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.dao.BoardDao;
import mysite.vo.BoardVo;
import mysite.vo.UserVo;

import java.io.IOException;
import java.util.Optional;

import static mysite.controller.ActionServlet.*;

public class ViewAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Long> boardId = Optional.of(Long.parseLong(request.getParameter("id"))); // Optional.ofNullable(request.getParameter("id")
        Long bID = boardId.orElseThrow(() -> new IllegalArgumentException("id 값이 없습니다."));
        BoardVo boardVo = new BoardDao().findById(bID);

        HttpSession session = request.getSession();
        if (session != null) {
            UserVo authUser = (UserVo) session.getAttribute("authUser");
            if (authUser != null) {
                Long authUserId = authUser.getId();
                Long bookUserId = boardVo.getUserId();
                if (authUserId.equals(bookUserId)) { // 본인이 쓴 글인 경우
                    request.setAttribute("isEditable", true);
                } else { // 본인이 쓴 글이 아닌 경우
                    request.setAttribute("isEditable", false);
                }
            } else {
                request.setAttribute("isEditable", false);
            }
        } else {
            request.setAttribute("isEditable", false);
        }

        // 조회수 증가
        new BoardDao().updateHitCount(boardVo);
        request.setAttribute("vo", boardVo);
        request.setAttribute("id", bID);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
        rd.forward(request, response);
    }
}
