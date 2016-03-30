package robert.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import robert.entities.EmailAddress;
import robert.entities.User;
import robert.repositories.UserRepository;
import robert.responses.BasicResponse;

/**
 * Created by robert on 25.03.16.
 */

@RestController
@RequestMapping("/register")
public class RegisterController {

    private static final Logger logger = Logger.getLogger(RegisterController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    BasicResponse registerNewUser(@RequestBody User user) {
        logger.info("new user request");
        BasicResponse response = new BasicResponse();

        if (userRepository.findByEmail(new EmailAddress(user.getEmail())) == null) {
            userRepository.save(user);
            logger.info("User " + user.getEmail() + " has beed registered");
            response.setResult(true);
        } else {
            logger.error("Given e-mail address already exists");
        }
        //List<BasicResponse> responses = new ArrayList<>();
        //responses.add(response);
        //return responses;
        return response;
    }
}
