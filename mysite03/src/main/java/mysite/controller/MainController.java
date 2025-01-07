package mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
