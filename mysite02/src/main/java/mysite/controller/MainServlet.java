package mysite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import mysite.controller.action.main.MainAction;

//@WebServlet({"/main", ""}) // /main을 입력하면 이곳으로 연결됨.
public class MainServlet extends ActionServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        String config = getServletConfig().getInitParameter("config"); // 초기 파라미터 설정을 위함.
//        System.out.println("MainController.init() called:" + config);

        super.init();
    }

    @Override
    protected Action getAction(String actionName) {
        return new MainAction();
    }
}

