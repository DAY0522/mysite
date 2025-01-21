package mysite.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.service.AdminService;
import mysite.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;

public class SiteInterceptor implements HandlerInterceptor {

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AdminVo adminVo = (AdminVo) request.getServletContext().getAttribute("vo");
        if (adminVo == null) {
            adminVo = adminService.getContent();
            request.getServletContext().setAttribute("vo", adminVo);
        }

        // locale
        String lang = localeResolver.resolveLocale(request).getLanguage();
        request.setAttribute("lang", lang);

        return true;
    }
}
