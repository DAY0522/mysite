package mysite.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;

import java.io.IOException;

public class EncodingFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;

    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("EncodingFilter.doFilter() called: request processing" );
        request.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);

//        System.out.println("EncodingFilter.doFilter() called: response processing" );

    }

    public void destroy() {

    }
}
