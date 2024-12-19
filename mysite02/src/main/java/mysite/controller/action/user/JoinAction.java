package mysite.controller.action.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.ActionServlet.Action;
import mysite.dao.UserDao;
import mysite.vo.UserVo;

import java.io.IOException;

public class JoinAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");

        UserVo vo = new UserVo();
        vo.setName(name);
        vo.setEmail(email);
        vo.setPassword(password);

        if ("male".equals(gender)) {
            vo.setGender("male");
        } else {
            vo.setGender("female");
        }

        new UserDao().insert(vo);
        response.sendRedirect(request.getContextPath() + "/user?a=joinsuccess");
    }
}
