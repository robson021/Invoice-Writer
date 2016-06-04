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
import robert.services.api.Mailer;
import robert.session.SessionData;

/**
 * Created by robert on 25.03.16.
 */

@RestController
@RequestMapping("/register")
public class RegisterController {

    private static final Logger logger = Logger.getLogger(RegisterController.class);

    private DbService dbService;
    private Mailer mailer;
    private SessionData sessionData;

    @Autowired
    public RegisterController(DbService dbService, Mailer mailer, SessionData sessionData) {
        this.dbService = dbService;
        this.mailer = mailer;
        this.sessionData = sessionData;
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public BasicResponse registerNewUser(@RequestBody SimpleUser user) {
        logger.info("New user request");
        BasicResponse response = new BasicResponse();
        user.setEmail(user.getEmail().trim());
        User u = null;
        try {
            u = dbService.findUserByEmail(user.getEmail());
        } catch (Exception e) {
            response.setText("Invalid e-mail pattern.");
            return response;
        }
        if (u == null) {
            System.out.println(user.toString());
            u = new User(user);
            dbService.saveUser(u);
            response.setResult(true);
            response.setText("You can login now.");

            // sending via thread for better performance. Waiting for mailer takes too long
            mailer.sendEmail(u.getEmail(), "Registration", "You have been registered", null);
            logger.info("User registered");
        } else {
            logger.error("Given e-mail is already taken!");
            response.setText("That e-mail is already used!");
        }
        return response;
    }

}
