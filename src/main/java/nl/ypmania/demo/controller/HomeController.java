package nl.ypmania.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    
    // Note that the default attribute name is the type name, "homeForm".
    @ModelAttribute
    public HomeForm createForm() {
        // This method will be called to initialize a form DTO instance,
        // which is then data-bound afterwards if it's a POST request. 
        return HomeForm.empty();
    }

    @RequestMapping(method = GET)
    public String showHome() {
        // Render the "/home" view on GET /
        // (looked up using the view resolver in WebConfig)
        return "/home"; 
    }

    @RequestMapping(method = POST)
    public String submitMessage(HomeForm form, RedirectAttributes redirectAttrs) {
        log.debug("Received a message: {}", form.getMessageFromUser());
        redirectAttrs.addFlashAttribute("message", form.getMessageFromUser());
        return "redirect:/"; // redirect back to the root again
    }
   
}
