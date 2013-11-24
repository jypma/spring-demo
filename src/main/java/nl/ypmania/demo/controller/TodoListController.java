package nl.ypmania.demo.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Collections;
import java.util.List;

import nl.ypmania.demo.todo.TodoItem;
import nl.ypmania.demo.util.ListWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/todos")
public class TodoListController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(TodoListController.class);

    @ModelAttribute("list")
    public List<TodoItem> list() {
        //TODO stub
        return Collections.singletonList(TodoItem.of("Hello"));
    }
    
    @RequestMapping(method = GET)
    public String showList() {
        return "/todos"; 
    }

    @RequestMapping(method = GET, produces = { "text/xml", "application/json" } )
    public @ResponseBody ListWrapper<TodoItem> renderList(@ModelAttribute("list") List<TodoItem> list) {
        log.debug("Listing");
        return ListWrapper.of(list);
    }
    
}
