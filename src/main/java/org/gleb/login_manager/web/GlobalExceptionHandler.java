package org.gleb.login_manager.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String error(Exception e) {
        System.out.println(e.getMessage());
        return "error.html";
    }

}
