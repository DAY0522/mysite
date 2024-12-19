package mysite.controller.action.guestbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.ActionServlet;
import mysite.dao.GuestbookDao;

import java.io.IOException;

public class DeleteAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String password = request.getParameter("password");

        new GuestbookDao().deleteByIdAndPassword(id, password);

        response.sendRedirect("/mysite02/guestbook");
    }
}
