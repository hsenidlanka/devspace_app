package hsenid.web.Controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
@EnableWebMvc
public class ExceptionHandlerController extends DefaultHandlerExceptionResolver {

    @ExceptionHandler(Exception.class)
    public String handleNoSuchMethodtFoundException(Exception ex){
        return "locations";
    }

}