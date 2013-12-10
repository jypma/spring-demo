package nl.ypmania.demo.controller.todo;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.UUID;

import nl.ypmania.demo.todo.TodoItem;
import nl.ypmania.demo.todo.TodoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todos/{id}")
public class ShowTodoController {
    private static final Logger log = LoggerFactory.getLogger(ShowTodoController.class);
    
    private final TodoService todoService;
    
    @Autowired
    public ShowTodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @ModelAttribute("item")
    public TodoItem item(@PathVariable("id") UUID id) {
        // This method will be called to initialize a form DTO instance,
        // which is then data-bound afterwards if it's a POST request.
        log.debug("Loading item with ID {}", id);
        return todoService.load(id);
    }    

    @RequestMapping(method = GET) 
    public String showEditPage () {
        return "/todo/edit";
    }
}
