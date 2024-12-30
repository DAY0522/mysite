package mysite.controller;

import mysite.security.Auth;
import mysite.security.AuthUser;
import mysite.service.BoardService;
import mysite.vo.BoardVo;
import mysite.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping("")
    public String list(@RequestParam(value = "p", defaultValue = "1") Integer page,
                       @RequestParam(value = "keyword", defaultValue = "") String keyword,
                       Model model) {

        Map<String, Object> contentsList = boardService.getContentsList(page, keyword);
        model.addAttribute("map", contentsList);

        return "board/list";
    }

    @Auth
    @RequestMapping(value = {"/write", "/write/{id}"}, method = RequestMethod.GET)
    public String write(@PathVariable(value = "id", required = false) Long id) {
        return "board/write";
    }

    @Auth
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(@AuthUser UserVo userVo,
                        BoardVo boardVo,
                        @RequestParam(value = "id", defaultValue = "") Long parentBoardId) {

        System.out.printf("id: %d\n", parentBoardId);
        boardVo.setUserId(userVo.getId());
        boardService.addContents(boardVo, parentBoardId);

        return "redirect:/board";
    }

    @RequestMapping("/view/{id}")
    public String view(Model model,
                       @PathVariable("id") Long id,
                       @AuthUser UserVo authUser) {
        BoardVo vo = boardService.getContents(id);
        model.addAttribute("vo", vo);
        if (authUser != null && authUser.getId() == vo.getUserId()) {
            model.addAttribute("isEditable", true);
        } else {
            model.addAttribute("isEditable", false);
        }

        return "board/view";
    }

    @Auth
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable("id") Long id, Model model) {
        BoardVo vo = boardService.getContents(id);
        model.addAttribute("vo", vo);
        return "board/modify";
    }

    @Auth
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public String modify(BoardVo vo, Model model) {
        boardService.updateContents(vo);
        model.addAttribute("vo", vo);
        return "board/view";
    }

    @Auth
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, @AuthUser UserVo authUser) {
        boardService.deleteContents(id, authUser.getId());
        return "redirect:/board";
    }
}
