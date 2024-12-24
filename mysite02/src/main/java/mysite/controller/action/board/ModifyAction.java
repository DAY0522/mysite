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

import static mysite.controller.ActionServlet.*;

public class ModifyAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if (authUser == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        Long boardId = Long.parseLong(request.getParameter("id"));
        BoardVo vo = new BoardDao().findById(boardId);
        request.setAttribute("vo", vo);
        request.setAttribute("id", boardId);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/modify.jsp");
        rd.forward(request, response);
    }
}

