package robert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import robert.entities.*;
import robert.responses.BasicResponse;
import robert.responses.Greetings;
import robert.responses.UserDataResponse;
import robert.responses.simpleentities.*;
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

    @RequestMapping(value = "/getusers")
    public ResponseEntity<User> getExampleUsers() {
        User user = dbService.findUserByEmail(new EmailAddress(DbService.getExampleUserEmail()));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/getcontractors_string")
    public String getExampleContractorsAsString() {
        User user = dbService.findUserByEmail(new EmailAddress(DbService.getExampleUserEmail()));
        System.out.println("Contractors as string:\n" + user.getContractorsAsString());
        return user.getContractorsAsString();
    }

    @RequestMapping(value = "/getsimplecontractors")
    public List<SimpleContractor> getSimpleContractorsList() {
        List<SimpleContractor> contractors = new ArrayList<>();
        contractors.add(new SimpleContractor());
        contractors.add(new SimpleContractor());
        contractors.add(new SimpleContractor());
        contractors.add(new SimpleContractor());

        return contractors;
    }

    @RequestMapping(value = "/getsimpleusers")
    public ResponseEntity<SimpleUser> getSimpleUser() {
        SimpleUser user = new SimpleUser(dbService.findUserByEmail(DbService.getExampleUserEmail()));
        return new ResponseEntity<SimpleUser>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/dataholder")
    public ResponseEntity<DataHolderResponse> getExampleDataHolder() {
        User user = dbService.findUserByEmail(DbService.getExampleUserEmail());

        List<SimpleContractor> simpleContractors = new ArrayList<>();
        List<SimpleSalesman> simpleSalesmen = new ArrayList<>();
        List<SimpleService> simpleServices = new ArrayList<>();

        for (Contractor c : user.getContractors()) {
            simpleContractors.add(c.getSimpleContractor());
        }
        for (Salesman s : user.getSalesmen()) {
            simpleSalesmen.add(s.getSimpleSalesman());
        }
        for (TheService se : user.getServices()) {
            simpleServices.add(se.getSimpleService());
        }

        DataHolderResponse holder = new DataHolderResponse();
        holder.setContractors(simpleContractors);
        holder.setSalesmen(simpleSalesmen);
        holder.setServices(simpleServices);

        return new ResponseEntity<DataHolderResponse>(holder, HttpStatus.OK);
    }

    @RequestMapping(value = "/dataupdate", method = RequestMethod.POST)
    public ResponseEntity<String> postDataToUpdate(@RequestBody DataHolderResponse holder) {
        System.out.println(holder.toString());
        return new ResponseEntity<String>("updated", HttpStatus.OK);
    }
}
