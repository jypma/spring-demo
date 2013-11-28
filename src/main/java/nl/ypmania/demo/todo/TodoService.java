package nl.ypmania.demo.todo;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private Map<UUID, TodoItem> all = new ConcurrentHashMap<UUID, TodoItem>();
    
    public TodoService() {
        save(TodoItem.create("Hello"));
    }
    
    public Iterable<TodoItem> listAll() {
        return all.values();        
    }

    public void save(TodoItem item) {
        all.put(item.getId(), item);
    }
}
