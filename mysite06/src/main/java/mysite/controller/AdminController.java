package mysite.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import mysite.service.AdminService;
import mysite.service.FileUploadService;
import mysite.vo.AdminVo;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final FileUploadService fileUploadService;

    private ServletContext servletContext;
    private final ApplicationContext applicationContext;

    public AdminController(AdminService adminService, FileUploadService fileUploadService, ServletContext servletContext, ApplicationContext applicationContext) {
        this.adminService = adminService;
        this.fileUploadService = fileUploadService;
        this.servletContext = servletContext;
        this.applicationContext = applicationContext;
    }

    @RequestMapping({"", "/main"})
    public String main(Model model, HttpServletRequest request) {

        return "admin/main";
    }

    @RequestMapping("/main/update")
    public String update(
            AdminVo vo,
            @RequestParam("file") MultipartFile file
        ) {

        String url = fileUploadService.restore(file);
        if (url != null) {
            vo.setProfile(url);
        }
        adminService.update(vo);

        // update servlet context bean
        servletContext.setAttribute("vo", vo); // 아예 새로운 걸로 바꿔치기

        // update application context bean
        AdminVo admin = applicationContext.getBean(AdminVo.class);
        BeanUtils.copyProperties(vo, admin);

        return "redirect:/admin";
    }

    @RequestMapping("/guestbook")
    public String guestbook() {
        return "admin/guestbook";
    }

    @RequestMapping("/board")
    public String board() {
        return "admin/board";
    }


    @RequestMapping("/user")
    public String user() {
        return "admin/user";
    }
}
