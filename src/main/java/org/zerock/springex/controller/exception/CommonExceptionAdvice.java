package org.zerock.springex.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@Log4j2
@ControllerAdvice  // 여러 @Controller 클래스에서 공유할 @ExceptionHandler, @InitBinder 또는 @ModelAttribute 메서드를 선언하는 클래스에 대한 @Component의 특수화.
public class CommonExceptionAdvice {

//    @ResponseBody
//    @ExceptionHandler(NumberFormatException.class)
//    public String exceptNumber(NumberFormatException numberFormatException){
//        log.error("-----------------------------------");
//        log.error(numberFormatException.getMessage());
//        return "NUMBER FORMAT EXCEPTION";
//    }

    // 거의 모든 예외 처리
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptCommon(Exception exception){
        log.error("-----------------------------------");
        log.error(exception.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");

        buffer.append("<li>" +exception.getMessage()+"</li>");

        Arrays.stream(exception.getStackTrace()).forEach(  stackTraceElement -> { buffer.append("<li>"+stackTraceElement+"</li>");});
        buffer.append("</ul>");
        return buffer.toString();
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){

        return "custom404";
    }

}
