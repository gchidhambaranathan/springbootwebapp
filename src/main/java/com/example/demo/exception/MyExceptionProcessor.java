package com.example.demo.exception;

import com.example.demo.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by GChidhambaranathan on 12/30/2017.
 */

@ControllerAdvice
public class MyExceptionProcessor {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage handleException(HttpServletRequest req, EntityNotFoundException e){
        System.out.println("Called in advice...");
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorMessage.setMessage(e.getMessage());
        errorMessage.setId(e.getId());
        errorMessage.setUrl(req.getRequestURI());
        return errorMessage;
    }
}
