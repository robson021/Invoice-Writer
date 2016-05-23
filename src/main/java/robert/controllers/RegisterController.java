package robert.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import robert.entities.User;
import robert.other.Mailer;
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

    private DbService dbService;
    private Mailer mailer;

    @Autowired
    public RegisterController(DbService dbService, Mailer mailer) {
        this.dbService = dbService;
        this.mailer = mailer;
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
            new Thread(new MailerTaskRunnable(u.getEmail())).start();
            logger.info("User registered");
        } else {
            logger.error("Given e-mail is already taken!");
            response.setText("That e-mail is already used!");
        }
        return response;
    }

    private class MailerTaskRunnable implements Runnable {
        private final String email;

        public MailerTaskRunnable(String email) {
            this.email = email;
        }

        @Override
        public void run() {
            try {
                mailer.sendEmail(email, "Registration - Invoice-Writer app", "Registration complete!", null);
                logger.info("Mail has been sent");
            } catch (Exception e) {
                logger.error("Email sender thread exception");
            }
        }
    }
}
