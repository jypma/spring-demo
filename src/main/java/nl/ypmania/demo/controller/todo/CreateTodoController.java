package nl.ypmania.demo.controller.todo;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import nl.ypmania.demo.controller.AbstractController;
import nl.ypmania.demo.todo.TodoItem;
import nl.ypmania.demo.todo.TodoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todos/create")
public class CreateTodoController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(CreateTodoController.class);
    
    private final TodoService todoService;
    
    @Autowired
    public CreateTodoController(TodoService todoService) {
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
    @RequestMapping(method = GET)
    public String showCreate() {
        return "/todo/create";
    }
    
    // By specifying @Valid, attempts to call this method will result in
    // Spring throwing a MethodArgumentNotValidException instead. This will by 
    // default result in an HTTP error 400 with a generic error message.
    @RequestMapping(method = POST, consumes = { MediaType.TEXT_XML, MediaType.APPLICATION_JSON } )
    public String createFromBody(@Valid @RequestBody TodoItem item) {
        log.debug("Creating REST todo item {}", item);
        todoService.create(item);
        // TODO return 201 http://stackoverflow.com/questions/3318912/what-is-the-preferred-way-to-specify-an-http-location-response-header-in-sprin
        return "redirect:/todos";
    }
    
    // By adding a BindingResult as argument, the method will be invoked also
    // when the @Valid-annotated object is not valid.
    @RequestMapping(method = POST, consumes = { MediaType.APPLICATION_FORM_URLENCODED } )
    public String createFromForm(@Valid @ModelAttribute("item") TodoItem item, BindingResult result) {
        if (result.hasErrors()) {
            log.debug("Received invalid todo item {}", item);
            return showCreate(); // re-render the form with error messages
        } else {
            log.debug("Creating form todo item {}", item);
            todoService.create(item);
            return "redirect:/todos";            
        }
    }
    
}
