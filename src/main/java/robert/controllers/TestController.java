package robert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import robert.entities.Contractor;
import robert.entities.EmailAddress;
import robert.entities.User;
import robert.responses.BasicResponse;
import robert.responses.Greetings;
import robert.responses.UserDataResponse;
import robert.services.DbService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 30.03.16.
 */
@RestController()
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DbService dbService;

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

    @RequestMapping(value = "/greetings", method = RequestMethod.POST)
    public BasicResponse postGreetings(@RequestBody String greetings) {
        System.out.println(greetings.toString());
        BasicResponse response = new BasicResponse();
        response.setResult(true);
        return response;
    }


    @RequestMapping(value = "/getuserdata")
    public UserDataResponse getTestUser() {
        System.out.println("GET on: /test/getuserdata");
        User user = dbService.findUserByEmail(new EmailAddress(DbService.getExampleUserEmail()));
        System.out.println("Test user found:\n" + user.toString());
        return new UserDataResponse(user);

    }

    @RequestMapping(value = "/getcontractors")
    public List<Contractor> getExampleContractors() {
        User user = dbService.findUserByEmail(new EmailAddress(DbService.getExampleUserEmail()));
        return user.getContractors();
    }

    @RequestMapping(value = "/getcontractors_string")
    public String getExampleContractorsAsString() {
        User user = dbService.findUserByEmail(new EmailAddress(DbService.getExampleUserEmail()));
        System.out.println("Contractors as string:\n" + user.getContractorsAsString());
        return user.getContractorsAsString();
    }
}
