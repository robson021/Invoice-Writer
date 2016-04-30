package robert.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import robert.entities.User;
import robert.responses.BasicResponse;
import robert.responses.simpleentities.DataHolderResponse;
import robert.responses.simpleentities.SimpleUser;
import robert.services.DbService;

import javax.servlet.http.HttpSession;

/**
 * Created by robert on 09.04.16.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private DbService dbService;


    @RequestMapping(value = "/loguser", method = RequestMethod.POST)
    public BasicResponse logUserIn(@RequestBody SimpleUser user, HttpSession session) {

        // TODO: 28.04.16 user agent

        logger.info("Login request\n" + user.toString());
        User dbUser = null;
        dbUser = dbService.findUserByEmail(user.getEmail().trim());

        if (dbUser != null) {
            if (session.getAttribute(user.getEmail()) != null) {
                logger.info("Session is still active. " + user.getEmail());
                DataHolderResponse holder = generateHolder(dbUser);
                holder.setText("Session is still active.");
                return holder;
            } else if (dbUser.getPasswdAsString().trim().equals(user.getPassword().trim())) {
                logger.info("passwords ok!");
                session.setAttribute(user.getEmail(), new Object()); // TODO: 30.04.16 set other class
                return this.generateHolder(dbUser);
            } else {
                BasicResponse response = new BasicResponse();
                logger.error("passwords do not match");
                response.setText("Invalid passwords.");
                return response;
            }

        } else {
            BasicResponse response = new BasicResponse();
            logger.info("Invalid data. User not found.");
            response.setText("User with given email does not exist.");
            return response;
        }
    }

    private DataHolderResponse generateHolder(User dbUser) {
        DataHolderResponse holder = new DataHolderResponse();
        holder.setServices(dbUser.getSimpleServices());
        holder.setContractors(dbUser.getSimpleContractors());
        holder.setSalesmen(dbUser.getSimpleSalesmen());
        holder.setResult(true);
        holder.setText("You are logged in!");
        return holder;
    }

}
