package mysite.controller.action.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.controller.ActionServlet.Action;
import mysite.dao.UserDao;
import mysite.vo.UserVo;

import java.io.IOException;

public class UpdateFormAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.printf("UpdateFormAction\n");

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

        UserVo vo = new UserDao().findById(authUser.getId());
        request.setAttribute("vo", vo);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/updateform.jsp");
        rd.forward(request, response);
    }
}