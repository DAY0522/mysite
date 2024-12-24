package mysite.controller.action.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.dao.BoardDao;
import mysite.vo.BoardVo;
import mysite.vo.UserVo;

import java.io.IOException;
import java.util.Optional;

import static mysite.controller.ActionServlet.Action;

public class WriteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
            rd.forward(request, response);
            return;
        }

        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if (authUser == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
            rd.forward(request, response);
            return;
        }

        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        Long userId = authUser.getId();

        BoardVo vo = new BoardVo();
        vo.setTitle(title);
        vo.setContents(contents);
        vo.setUserId(userId);

        BoardDao boardDao = new BoardDao();
        String boardId = request.getParameter("id");
        if (boardId != null && !boardId.trim().isEmpty()) { // 답글 등록
            Long bId = Long.parseLong(boardId);

            BoardVo originVo = boardDao.findById(bId); // 기존 게시글 정보 불러오기
            vo.setG_no(originVo.getG_no());
            vo.setO_no(originVo.getO_no()+1);
            vo.setDepth(originVo.getDepth()+1);

            boardDao.updateGroupOrder(vo);
        } else { // 새글 등록
            vo.setG_no(boardDao.findLastGId()+1);
        }
        boardDao.insert(vo);
        response.sendRedirect(request.getContextPath() + "/board");
    }
}
