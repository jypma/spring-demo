package nl.ypmania.demo.todo;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import nl.ypmania.demo.todo.validate.Without;

import org.hibernate.validator.constraints.NotBlank;

@XmlRootElement(name = "TodoItem")
@XmlAccessorType(XmlAccessType.NONE)
public class TodoItem {
    public static TodoItem create(String title) {
        return new TodoItem(UUID.randomUUID(), title);
    }
    
    public static TodoItem empty() {
        return new TodoItem(UUID.randomUUID(), null);
    }
    
    @XmlElement(name = "ID")
    private UUID id;
    
    @NotBlank
    @XmlElement(name = "Title")
    @Without("at some point")
    private String title;
    
    public String getTitle() {
        return title;
    }
    
    public UUID getId() {
        return id;
    }
    
    /**
     * Returns a copy of this TodoItem that is guaranteed to have a not-null ID,
     * setting it to a random UUID if null. 
     */
    public TodoItem withId() {
        return (id != null) ? this : new TodoItem (UUID.randomUUID(), title); 
    }
    
    public TodoItem withId(UUID id) {
        return new TodoItem (id, title);
    }

    private TodoItem() {}
    
    private TodoItem(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "TodoItem [id=" + id + ", title=" + title + "]";
    }
}
