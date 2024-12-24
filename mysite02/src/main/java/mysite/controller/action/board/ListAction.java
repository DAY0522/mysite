package mysite.controller.action.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.dao.BoardDao;
import mysite.vo.BoardVo;

import java.io.IOException;
import java.util.List;

import static mysite.controller.ActionServlet.*;

public class ListAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer page = 1;
        if (request.getParameter("p") != null) {
            page = Integer.parseInt(request.getParameter("p"));
        }
        BoardDao boardDao = new BoardDao();
        List<BoardVo> list = boardDao.findAll(page);
        Integer totalCount = boardDao.findCountAll();
        Integer endPage = boardDao.findEndPage();

        Integer currentStartPage = Math.max(1, page - 2); // 최소 1페이지에서 시작
        Integer currentEndPage = Math.min(endPage, currentStartPage + 4);

        if (currentEndPage > endPage) {
            currentEndPage = endPage;
            currentStartPage = Math.max(1, currentEndPage - 4);
        }

        if (currentEndPage == endPage) {
            currentStartPage = Math.max(1, endPage - 4);
        }

        // request에 추가: 전체 글 개수
        request.setAttribute("page_size", BoardDao.PAGE_SIZE);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("list", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("currentStartPage", currentStartPage);
        request.setAttribute("currentEndPage", currentEndPage);
        request.setAttribute("endPage", endPage);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
        rd.forward(request, response);
    }
}
