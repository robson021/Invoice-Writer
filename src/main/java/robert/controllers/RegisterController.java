package robert.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import robert.entities.User;
import robert.responses.BasicResponse;
import robert.responses.simpleentities.SimpleUser;
import robert.services.DbService;

/**
 * Created by robert on 25.03.16.
 */

@RestController
@RequestMapping("/register")
public class RegisterController {

    private static final Logger logger = Logger.getLogger(RegisterController.class);

    @Autowired
    private DbService dbService;

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public BasicResponse registerNewUser(@RequestBody SimpleUser user) {
        logger.info("New user request");
        BasicResponse response = new BasicResponse();
        User u = null;
        u = dbService.findUserByEmail(user.getEmail());
        if (u == null) {
            System.out.println(user.toString());
            u = new User(user);
            dbService.saveUser(u);
            response.setResult(true);
            logger.info("User registered");
        } else {
            logger.error("Given e-mail is already taken!");
        }
        return response;
    }
}
