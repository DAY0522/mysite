package mysite.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.dao.UserDao;
import mysite.vo.UserVo;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("a");

        if ("joinform".equals(action)) { // /user?a=joinform(GET)
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/joinform.jsp");
            rd.forward(request, response);
        } else if ("loginform".equals(action)) { // /user?a=loginform
            // success로 redirect
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
            rd.forward(request, response);
        } else if ("joinsuccess".equals(action)) { // /user?a=joinsuccess
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/joinsuccess.jsp");
            rd.forward(request, response);
        } else if ("join".equals(action)) { // /user?a=join(POST)
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");

            UserVo vo = new UserVo();
            vo.setName(name);
            vo.setEmail(email);
            vo.setPassword(password);
            if ("남".equals(gender)) {
                vo.setGender("male");
            } else {
                vo.setGender("female");
            }

            new UserDao().insert(vo);
            response.sendRedirect(request.getContextPath() + "/user?a=joinsuccess");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}