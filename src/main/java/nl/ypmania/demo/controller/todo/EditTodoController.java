package nl.ypmania.demo.controller.todo;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.UUID;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import nl.ypmania.demo.todo.TodoItem;
import nl.ypmania.demo.todo.TodoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

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
    @RequestMapping(value="/save", method = POST, consumes = { MediaType.APPLICATION_FORM_URLENCODED } )
    public String createFromForm(@Valid @ModelAttribute("item") TodoItem item, BindingResult result) {
        if (result.hasErrors()) {
            log.debug("Received invalid todo item {}", item);
            return showCreate(); // re-render the form with error messages
        } else {
            item = item.withId();
            log.debug("Creating form todo item {}", item);
            todoService.save(item);
            return "redirect:/todos";            
        }
    }
        
    // ----------------- REST methods come here ----------------------------
    
    @RequestMapping(value="/{id}", method = PUT, consumes = { MediaType.TEXT_XML, MediaType.APPLICATION_JSON } )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put (@PathVariable("id") UUID id, @Valid @RequestBody TodoItem item, UriComponentsBuilder b) {
        item = item.withId(id);
        log.debug ("Storing REST todo item {}", item);
        todoService.save(item);
    }
    
    // By specifying @Valid, attempts to call this method will result in
    // Spring throwing a MethodArgumentNotValidException instead. This will by 
    // default result in an HTTP error 400 with a generic error message.
    @RequestMapping(value="/create", method = POST, consumes = { MediaType.TEXT_XML, MediaType.APPLICATION_JSON } )
    public ResponseEntity<?> createFromBody(@Valid @RequestBody TodoItem item, UriComponentsBuilder b) {
        item = item.withId();
        log.debug("Creating REST todo item {}", item);
        todoService.save(item);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(b.path("/todos/{id}").buildAndExpand(item.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
}
