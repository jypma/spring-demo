package nl.ypmania.demo.todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private List<TodoItem> all = new ArrayList<TodoItem>();
    
    public TodoService() {
        all.add(TodoItem.of("Hello"));
    }
    
    public List<TodoItem> listAll() {
        return Collections.unmodifiableList(all);        
    }

    public void create(TodoItem item) {
        all.add(item);
    }
}
