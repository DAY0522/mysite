package mysite.exception;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Log log = LogFactory.getLog(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handler(Model model, Exception e) {

        // 1. logging
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        log.error(errors.toString());

        // 2. 사과(종료)
        model.addAttribute("errors", errors.toString());
        return "errors/exception";
    }
}
