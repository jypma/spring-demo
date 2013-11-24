package nl.ypmania.demo.config;

import static org.junit.Assert.*;
import nl.ypmania.demo.todo.TodoItem;
import nl.ypmania.demo.util.ListWrapper;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;

public class WebConfigTest {
    @Test
    public void xml_converter_should_marshall_wrapped_todo_list() {
        MarshallingHttpMessageConverter xml = new WebConfig().xmlConverter();
        assertTrue(xml.supports(TodoItem.class));
        assertTrue(xml.canWrite(TodoItem.class, MediaType.APPLICATION_XML));
        assertTrue(xml.canWrite(ListWrapper.class, MediaType.APPLICATION_XML));
    }

}
