package robert.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import robert.InvoiceWriterApplication;
import robert.entities.User;
import robert.other.SessionData;
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

        // TODO: 28.04.16 user agent?

        logger.info("Login request\n" + user.toString());
        User dbUser = null;
        dbUser = dbService.findUserByEmail(user.getEmail().trim());

        if (dbUser != null) {
            if (!session.isNew()) { // old session
                logger.info("Session is still active.\n" + session.getAttribute(user.getEmail()).toString() +
                        "\nSession id: " + session.getId());
                DataHolderResponse holder = generateHolder(dbUser);
                holder.setText("Session is still active.");
                return holder;
            } else if (dbUser.getPasswdAsString().equals(user.getPassword())) { // new session
                logger.info("passwords ok!");
                // session bean
                SessionData data = InvoiceWriterApplication.ctx.getBean("sessionData", SessionData.class);
                data.setEmail(dbUser.getEmail());
                session.setAttribute(dbUser.getEmail(), data);
                logger.info("Session info: " + data.toString());
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
