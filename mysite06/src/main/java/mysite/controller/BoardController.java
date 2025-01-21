package mysite.controller;

import mysite.service.BoardService;
import mysite.vo.BoardVo;
import mysite.vo.UserVo;
import org.springframework.security.core.Authentication;
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

        return "board/index";
    }

    @RequestMapping(value = {"/write", "/write/{id}"}, method = RequestMethod.GET)
    public String write(@PathVariable(value = "id", required = false) Long id) {
        return "board/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(Authentication authentication,
                        BoardVo boardVo,
                        @RequestParam(value = "id", defaultValue = "") Long parentBoardId) {

        UserVo authUser = (UserVo) authentication.getPrincipal();
        boardVo.setUserId(authUser.getId());
        boardService.addContents(boardVo, parentBoardId);

        return "redirect:/board";
    }

    @RequestMapping("/view/{id}")
    public String view(Model model,
                       @PathVariable("id") Long id,
                       Authentication authentication) {
        BoardVo vo = boardService.getContents(id);
        model.addAttribute("vo", vo);
        if (authentication == null || !authentication.isAuthenticated()) {
            model.addAttribute("isEditable", false);
        } else {
            model.addAttribute("isEditable", true);
        }

        return "board/view";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable("id") Long id, Model model) {
        BoardVo vo = boardService.getContents(id);
        model.addAttribute("vo", vo);
        return "board/modify";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public String modify(BoardVo vo, Model model) {
        boardService.updateContents(vo);
        model.addAttribute("vo", vo);
        return "board/view";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Authentication authentication) {
        UserVo authUser = (UserVo) authentication.getPrincipal();
        boardService.deleteContents(id, authUser.getId());
        return "redirect:/board";
    }
}
