package mysite.controller;

import mysite.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

//    @Autowired
//    ApplicationContext applicationContext;

    @RequestMapping({"/", "/main"})
    public String index(Model model) {
//        AdminVo vo = applicationContext.getBean(AdminVo.class);
//        System.out.println(vo);
        return "main/index";
    }

    @ResponseBody
    @RequestMapping("/msg01")
    public String message01() {
        return "Hello World";
    }

    @ResponseBody
    @RequestMapping("/msg02")
    public String message02() {
        return "안녕 세상";
    }
    @ResponseBody
    @RequestMapping("/msg03")
    public Object message03() {
        UserVo vo = new UserVo();
        vo.setId(10L);
        vo.setName("둘리");
        vo.setEmail("dooly@gmail.com");
        return vo;
    }
}
