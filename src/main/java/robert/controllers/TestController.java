package robert.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by robert on 26.03.16.
 */

@Controller
public class TestController {

    @RequestMapping("/test")
    public String goToTestPage() {
        return "test";
    }
}
