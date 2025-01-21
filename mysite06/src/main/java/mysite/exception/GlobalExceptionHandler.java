package mysite.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.dto.JsonResult;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Log log = LogFactory.getLog(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public void handler(
            HttpServletRequest request, // accept(application/json, text/xml 등)를 들여다보기 위해 Http Servlet을 분리함.
            HttpServletResponse response,
            Exception e) throws Exception {

        // 1. logging
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        log.error(errors.toString());

        // 2. 요청 구분
        // json 요청: request header의 accept:  application/json (o)
        // html 요청: request header의 accept:  application/json (x)
        String accept = request.getHeader("accept");

        if (accept.matches(".*application/json.*")) { // application/json이 있는지를 확인
            // 3. JSON 응답
            JsonResult jsonResult = JsonResult.fail((e instanceof NoHandlerFoundException) ? "Unknown API URL" : errors.toString());
            String jsonString = new ObjectMapper().writeValueAsString(jsonResult);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; charset=utf-8");
            OutputStream os = response.getOutputStream();
            os.write(jsonString.getBytes("utf-8"));
            os.close();

            return;
        }
        // 4. 사과 페이지
        if (e instanceof NoHandlerFoundException) {
            request.getRequestDispatcher("/WEB-INF/views/errors/404.jsp").forward(request, response);
        } else {
            request.setAttribute("errors", errors.toString());
            request.getRequestDispatcher("/WEB-INF/views/errors/exception.jsp").forward(request, response);
        }
    }
}
