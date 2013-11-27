package nl.ypmania.demo.controller;

import java.util.ArrayList;
import java.util.List;

import nl.ypmania.demo.error.ValidationError;
import nl.ypmania.demo.util.ListWrapper;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ValidationExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ListWrapper<ValidationError> processValidationException(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        for (FieldError error: ex.getBindingResult().getFieldErrors()) {
            // We don't resolve the error code into a message because:
            // - It will be API consumers reading the error, codes are more semantical
            // - The model attribute name under which the message bundles are looked up
            //   is always the class name, making it hard/awkward to share messages between
            //   the UI and the API.
            errors.add (ValidationError.asCode(error));
        }
        return ListWrapper.of(errors);
    }

}
