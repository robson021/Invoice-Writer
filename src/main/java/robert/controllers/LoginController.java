package robert.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import robert.entities.User;
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
    public DataHolderResponse logUserIn(@RequestBody SimpleUser user, HttpSession session) {

        String tmp = (String) session.getAttribute("user");

        // TODO: 28.04.16 user agent

        logger.info("Login request\n" + user.toString());
        User dbUser = null;
        session.setAttribute("user", "test");
        DataHolderResponse holder = new DataHolderResponse();
        dbUser = dbService.findUserByEmail(user.getEmail());
        if (dbUser != null) {
            if (dbUser.getPasswdAsString().trim().equals(user.getPassword().trim())) {
                logger.info("passwords ok!");
            } else {
                logger.error("passwords do not match");
                holder.setText("Invalid passwords.");
                return holder;
            }
            holder.setServices(dbUser.getSimpleServices());
            holder.setContractors(dbUser.getSimpleContractors());
            holder.setSalesmen(dbUser.getSimpleSalesmen());
            holder.setResult(true);
            holder.setText("You are logged in!");
        } else {
            logger.info("Invalid data. User not found.");
            holder.setText("User with given email does not exist.");
        }

        return holder;
    }

}
