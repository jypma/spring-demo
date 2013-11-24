package nl.ypmania.demo.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Base class for our controllers, so Spring uses direct field access for populating
 * the form DTOs. This allows them to be immutable objects from a Java perspective.
 */
public abstract class AbstractController {
    @InitBinder
    public void configureBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }
}
