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
        if (vo == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }
        vo.setId(authUser.getId());
        System.out.println("authUser: " + vo);
        request.setAttribute("vo", vo);

        System.out.println("화긴1");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/updateform.jsp");
        System.out.println("화긴2");

        rd.forward(request, response);
        System.out.println("화긴3");

    }
}

