package nl.ypmania.demo.controller.todo;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.ws.rs.core.MediaType;

import nl.ypmania.demo.controller.AbstractController;
import nl.ypmania.demo.todo.TodoItem;
import nl.ypmania.demo.todo.TodoService;
import nl.ypmania.demo.util.ListWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/todos")
public class TodoListController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(TodoListController.class);
    
    private final TodoService todoService;
    
    @Autowired
    public TodoListController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ModelAttribute("list")
    public List<TodoItem> list() {
        return todoService.listAll();
    }
    
    @RequestMapping(method = GET)
    public String showList() {
        log.debug("showing list");
        return "/todo/list"; 
    }

    @RequestMapping(method = GET, produces = { MediaType.TEXT_XML, MediaType.APPLICATION_JSON } )
    public @ResponseBody ListWrapper<TodoItem> getList(@ModelAttribute("list") List<TodoItem> list) {
        log.debug("getting list");
        return ListWrapper.of(list);
    }
    
}
