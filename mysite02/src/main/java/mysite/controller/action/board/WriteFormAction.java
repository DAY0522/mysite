package mysite.controller.action.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.vo.UserVo;

import java.io.IOException;
import java.util.Optional;

import static mysite.controller.ActionServlet.*;

public class WriteFormAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
            rd.forward(request, response);
            return;
        }

        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if (authUser == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
            rd.forward(request, response);
            return;
        }

        Optional<Long> optionalBoardId = Optional.ofNullable(request.getParameter("id")).map(Long::parseLong);
        if (optionalBoardId.isPresent()) {
            request.setAttribute("id", optionalBoardId.get());
        } else {
            request.setAttribute("id", null);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/write.jsp");
        rd.forward(request, response);
    }
}
