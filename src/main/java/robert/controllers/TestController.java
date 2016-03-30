package robert.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import robert.responses.BasicResponse;
import robert.responses.Greetings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 30.03.16.
 */

@RestController()
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/greetings", method = RequestMethod.GET)
    public List<Greetings> getGreetings() {
        Greetings greetings = new Greetings();
        List<Greetings> greetingsList = new ArrayList<>();
        greetingsList.add(greetings);
        Greetings greetings2 = new Greetings();
        greetings2.setText("greetings #2");
        Greetings greetings3 = new Greetings();
        greetings3.setText("Greetings #3");
        greetingsList.add(greetings2);
        greetingsList.add(greetings3);
        return greetingsList;
    }

    @RequestMapping(value = "/greetings/post", method = RequestMethod.POST)
    public BasicResponse postGreetings(@RequestBody Greetings greetings) {
        System.out.println(greetings.toString());
        BasicResponse response = new BasicResponse();
        response.setResult(true);
        return response;
    }
}
