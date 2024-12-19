package mysite.controller.action.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.dao.UserDao;
import mysite.vo.UserVo;

import java.io.IOException;

import static mysite.controller.ActionServlet.*;

public class UpdateAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("UpdateAction");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");

        HttpSession session = request.getSession();
        UserVo vo = (UserVo) session.getAttribute("authUser");

        vo.setId(vo.getId());
        vo.setName(name);
        vo.setPassword(password);
        vo.setGender(gender);
        if (vo.getPassword().isEmpty()){
            new UserDao().updateNoPassword(vo);
        } else {
            new UserDao().update(vo);
        }

        response.sendRedirect(request.getContextPath() + "/user?a=updateform");
    }
}
