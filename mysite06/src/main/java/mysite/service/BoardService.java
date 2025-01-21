package mysite.service;

import mysite.repository.BoardRepository;
import mysite.vo.BoardVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static mysite.repository.BoardRepository.PAGE_SIZE;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void addContents(BoardVo vo, Long parentBoardId) {

        if (parentBoardId != null) { // 답글 등록
            BoardVo originVo = boardRepository.findById(parentBoardId);
            vo.setG_no(originVo.getG_no());
            vo.setO_no(originVo.getO_no()+1);
            vo.setDepth(originVo.getDepth()+1);

            boardRepository.updateGroupOrder(vo);
        } else { // 새글 등록
            System.out.println("결과 출력: " + boardRepository.findLastGId());
            Integer lastGId = boardRepository.findLastGId();
            Integer gNo = lastGId != null ? lastGId : 0;
            vo.setG_no(gNo+1);
        }

        boardRepository.insert(vo);
    }

    public BoardVo getContents(Long id) {
        boardRepository.updateHitCount(id);
        return boardRepository.findById(id);
    }

  /*  public BoardVo getContents(Long id, Long userId) {
        return boardRepository.findByIdAndUserId(id, userId);
    }*/

    public void updateContents(BoardVo vo) {
        boardRepository.update(vo);
    }

    public void deleteContents(Long id, Long userId) {
        boardRepository.deleteByIdAndUserId(id, userId);
    }

    public Map<String, Object> getContentsList(int cuurentPage, String keyword) {
        List<BoardVo> list = null;

        // view의 pagination를 위한 데이터 값 계산
        List<BoardVo> contentsList = boardRepository.findByPageAndKeyword(cuurentPage, keyword);
        System.out.println("contentList: " + contentsList);

        Map<String, Object> map = new HashMap<>();

        Integer totalCount = boardRepository.findCountAll(keyword);
        Integer endPage = (int) Math.ceil(totalCount / (double) PAGE_SIZE);
        Integer currentStartPage = Math.max(1, cuurentPage - 2); // 최소 1페이지에서 시작
        Integer currentEndPage = Math.min(endPage, currentStartPage + 4);

        if (currentEndPage > endPage) {
            currentEndPage = endPage;
            currentStartPage = Math.max(1, currentEndPage - 4);
        }

        if (currentEndPage == endPage) {
            currentStartPage = Math.max(1, endPage - 4);
        }

        map.put("page_size", PAGE_SIZE);
        map.put("totalCount", totalCount);
        map.put("list", contentsList);
        map.put("currentPage", cuurentPage);
        map.put("currentStartPage", currentStartPage);
        map.put("currentEndPage", currentEndPage);
        map.put("endPage", endPage);
        map.put("search", true);

        return map;
    }
}
