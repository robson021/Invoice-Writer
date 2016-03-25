package robert.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by robert on 25.03.16.
 */

@Controller
public class RegisterControler {

    @RequestMapping("/register")
    String goRegister() {
        return "register";
    }
}
