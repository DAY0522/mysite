package mysite.controller;

import mysite.service.GuestbookService;
import mysite.vo.GuestbookVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

    private GuestbookService guestbookService;

    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @RequestMapping("")
    public String List(Model model) {
        model.addAttribute("list", guestbookService.getContentsList());
        return "guestbook/list";
    }

    @RequestMapping("/insert")
    public String insert(GuestbookVo guestbookVo) {
        guestbookService.addContents(guestbookVo);
        return "redirect:/guestbook";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        return "guestbook/delete";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id,
                         @RequestParam("password") String password) {

        guestbookService.deleteContents(id, password);

        return "redirect:/guestbook";
    }
}
