package nl.ypmania.demo.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Configures Spring to use direct field access for populating
 * the form DTOs. This allows them to be immutable objects from a Java perspective.
 * 
 * Note that this does not affect DTOs being constructed by Jackson (from JSON)
 * or JAXB (from XML).
 */
@ControllerAdvice
public class DirectBinderInitializer {
    @InitBinder
    public void configureBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }
}
