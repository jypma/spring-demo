@XmlSchema(
        namespace = NAMESPACE, 
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns={@XmlNs(prefix=PREFIX, namespaceURI=NAMESPACE)})  

package nl.ypmania.demo.util;
import javax.xml.bind.annotation.*;
import static nl.ypmania.demo.util.XMLUtil.*;