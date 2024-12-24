package mysite.controller.action.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.dao.BoardDao;
import mysite.vo.BoardVo;

import java.io.IOException;
import java.util.List;

import static mysite.controller.ActionServlet.Action;
import static mysite.dao.BoardDao.PAGE_SIZE;

public class SearchListAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer page = 1;
        if (request.getParameter("p") != null) {
            page = Integer.parseInt(request.getParameter("p"));
        }

        String keyword = request.getParameter("keyword"); // 검색 키워드

        BoardDao boardDao = new BoardDao();
        List<BoardVo> list = boardDao.findAllWithKeyword(page, keyword);
        Integer totalCount = boardDao.findCountAll();
        Integer endPage = (int) Math.ceil(totalCount / (double) PAGE_SIZE);

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
        request.setAttribute("page_size", totalCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("list", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("currentStartPage", currentStartPage);
        request.setAttribute("currentEndPage", currentEndPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("search", true);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
        rd.forward(request, response);
    }
}
