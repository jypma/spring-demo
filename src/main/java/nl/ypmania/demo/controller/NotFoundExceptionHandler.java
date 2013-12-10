package nl.ypmania.demo.controller;

import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundExceptionHandler {
    
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String processNotFoundException(NoSuchElementException ex) {
        return StringUtils.defaultString(ex.getMessage(), "The requested object was not found.");
    }

}
