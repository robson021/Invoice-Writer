package robert.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by robert on 25.03.16.
 */

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/home", "/start", "/index"})
    public String goHome() {
        return "home";
    }
}
