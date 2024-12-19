package mysite.controller.action.guestbook;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.dao.GuestbookDao;
import mysite.vo.GuestbookVo;

import java.io.IOException;
import java.util.List;

import static mysite.controller.ActionServlet.*;

public class ListAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GuestbookVo> list = new GuestbookDao().findAll();
        request.setAttribute("list", list);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
        rd.forward(request, response);
    }
}
