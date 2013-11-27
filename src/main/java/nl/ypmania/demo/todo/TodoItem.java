package nl.ypmania.demo.todo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

@XmlRootElement(name = "TodoItem")
@XmlAccessorType(XmlAccessType.NONE)
public class TodoItem {
    public static TodoItem of(String title) {
        return new TodoItem(title);
    }
    
    public static TodoItem empty() {
        return new TodoItem(null);
    }
    
    @NotBlank
    @XmlElement(name = "Title")
    private String title;
    
    public String getTitle() {
        return title;
    }

    private TodoItem() {}
    
    private TodoItem(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TodoItem [title=" + title + "]";
    }
}
