package nl.ypmania.demo.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @RequestMapping(method = GET)
    public String showHome() {
        // Render the "/home" view on GET /
        // (looked up using the view resolver in WebConfig)
        return "/home"; 
    }

}
