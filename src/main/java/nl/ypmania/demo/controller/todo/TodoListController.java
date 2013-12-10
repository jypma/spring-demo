package nl.ypmania.demo.controller.todo;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import nl.ypmania.demo.todo.TodoItem;
import nl.ypmania.demo.todo.TodoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todos")
public class TodoListController {
    private static final Logger log = LoggerFactory.getLogger(TodoListController.class);
    
    private final TodoService todoService;
    
    @Autowired
    public TodoListController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ModelAttribute("list")
    public Iterable<TodoItem> list() {
        return todoService.listAll();
    }
    
    @RequestMapping(method = GET)
    public String showList() {
        log.debug("showing list");
        return "/todo/list"; 
    }

}
