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

public class LoginAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginAction");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserVo vo = new UserDao().findByEmailAndPassword(email, password);

        /// 로그인 실패
        if (vo == null) {
            // 방법1
//            response.sendRedirect(request.getContextPath() + "/user?a=loginform&result=fail");

            // 방법2
            request.setAttribute("result", "fail");
            request.setAttribute("email", email);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
            rd.forward(request, response);
        }

        // 로그인 처리
        HttpSession session = request.getSession();
        session.setAttribute("authUser", vo);

        response.sendRedirect(request.getContextPath());
    }
}
