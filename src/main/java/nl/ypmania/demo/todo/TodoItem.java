package nl.ypmania.demo.todo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TodoItem")
@XmlAccessorType(XmlAccessType.NONE)
public class TodoItem {
    public static TodoItem of (String title) {
        return new TodoItem (title);
    }
    
    @XmlElement(name = "Title")
    private String title;
    
    public String getTitle() {
        return title;
    }

    private TodoItem() {}
    
    private TodoItem(String title) {
        this.title = title;
    }
}
