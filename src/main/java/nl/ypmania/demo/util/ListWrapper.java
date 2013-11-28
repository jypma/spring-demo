package nl.ypmania.demo.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name="List")
@XmlAccessorType(XmlAccessType.NONE)
public class ListWrapper<T> {
    public static <T> ListWrapper<T> of (Iterable<T> items) {
        ArrayList<T> l = new ArrayList<T>();
        for (T t: items) l.add(t);
        return new ListWrapper<T>(l);
    }
    
    public static <T> ListWrapper<T> of (List<T> items) {
        return new ListWrapper<T>(items);
    }
    
    // We use a JSON-only annotation to define the JSON name for the list.
    // In XML-land, all the items in the list will just use the name of the item tag,
    // and there is no wrapper for the list itself.
    @JsonProperty("Items")
    @XmlAnyElement(lax=true)
    private List<T> items;

    private ListWrapper() {}
    
    private ListWrapper(List<T> items) {
        this.items = items;
    }
    
    public List<T> getItems() {
        return items;
    }
}
