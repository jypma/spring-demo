package nl.ypmania.demo.error;

import java.util.Locale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

@XmlRootElement(name="ValidationError")
@XmlAccessorType(XmlAccessType.NONE)
public class ValidationError {
 // inspired by
 // http://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-adding-validation-to-a-rest-api/    
    
    @XmlElement(name="Path")
    private String path;
    
    @XmlElement(name="Message")
    private String message;
    
    /**
     * Creates a new validation error instance by looking up the error's message
     * in the given message source.
     */
    public static ValidationError asMessage (FieldError error, MessageSource messages) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messages.getMessage(error, currentLocale);
        
        if (localizedErrorMessage.equals(error.getDefaultMessage())) {
            String[] fieldErrorCodes = error.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }
        
        return new ValidationError(error.getField(), localizedErrorMessage);
    }
    
    /** 
     * Creates a new validation error instance by taking the field error's 
     * first error code as message.
     */
    public static ValidationError asCode (FieldError error) {
        return new ValidationError(error.getField(), error.getCodes()[0]);
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getPath() {
        return path;
    }
    
    private ValidationError(String path, String message) {
        this.path = path;
        this.message = message;
    }
    
    private ValidationError() {}
    
}
