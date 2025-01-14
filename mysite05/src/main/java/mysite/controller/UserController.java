package mysite.controller;

import jakarta.validation.Valid;
import mysite.security.Auth;
import mysite.security.AuthUser;
import mysite.service.UserService;
import mysite.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(@ModelAttribute("user") UserVo user) {
        return "user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(@ModelAttribute("user") @Valid UserVo user, BindingResult result, Model model) {
        if (result.hasErrors()) { // 에러 발생
            model.addAllAttributes(result.getModel());

            return "user/join";
        }
        userService.join(user);
        return "redirect:/user/joinsuccess";
    }

    @RequestMapping("/joinsuccess")
    public String joinSuccess() {
        return "user/joinsuccess";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @Auth
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@AuthUser UserVo authUser, Model model) {
        UserVo userVo = userService.getUser(authUser.getId());

        model.addAttribute("vo", userVo);
        return "user/update";
    }

    @Auth
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@AuthUser UserVo authUser, UserVo userVo) {
        userVo.setId(authUser.getId());
        userService.update(userVo);

        authUser.setName(userVo.getName());
        return "redirect:/user/update";
    }

    @RequestMapping("/auth")
    public void auth() {

    }

    @RequestMapping("/logout")
    public void logout() {

    }
}
