package nl.ypmania.demo.controller.todo;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import nl.ypmania.demo.todo.TodoItem;
import nl.ypmania.demo.todo.TodoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todos")
public class EditTodoController {
    private static final Logger log = LoggerFactory.getLogger(EditTodoController.class);
    
    private final TodoService todoService;
    
    @Autowired
    public EditTodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ModelAttribute("item")
    public TodoItem item() {
        // This method will be called to initialize a form DTO instance,
        // which is then data-bound afterwards if it's a POST request. 
        return TodoItem.empty();
    }
    
    // The URL is specified at controller-level, since both methods in this controller
    // work on the same URL.
    @RequestMapping(value="/create", method = GET)
    public String showCreate() {
        return "/todo/edit";
    }
    
    // By adding a BindingResult as argument, the method will be invoked also
    // when the @Valid-annotated object is not valid.
    @RequestMapping(value="/save", method = POST)
    public String createFromForm(@ModelAttribute("item") TodoItem item, BindingResult result) {
//        if (result.hasErrors()) {
//            log.debug("Received invalid todo item {}", item);
//            return showCreate(); // re-render the form with error messages
//        } else {
            item = item.withId();
            log.debug("Creating form todo item {}", item);
            todoService.save(item);
            return "redirect:/todos";            
//        }
    }
        
}
