package nl.ypmania.demo.todo.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import nl.ypmania.demo.todo.TodoService;

public class WithoutValidator implements ConstraintValidator<Without, String> {
    
    @Autowired
    public WithoutValidator(TodoService todoService) {
        this.todoService = todoService;
    }
    
    private TodoService todoService;
    private Without annotation;

    @Override
    public void initialize(Without annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("isValid : " + todoService);
        return !StringUtils.containsIgnoreCase(value, annotation.value());
    }

}
