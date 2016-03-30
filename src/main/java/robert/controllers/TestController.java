package robert.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import robert.responses.Greetings;

/**
 * Created by robert on 30.03.16.
 */

@RestController("/test")
public class TestController {

    @RequestMapping(value = "/greetings", method = RequestMethod.GET)
    public ResponseEntity<Greetings> getGreetings() {
        Greetings greetings = new Greetings();
        return new ResponseEntity<>(greetings, HttpStatus.OK);
    }
}
