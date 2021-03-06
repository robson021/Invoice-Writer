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
import robert.session.SessionData;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by robert on 09.04.16.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    private DbService dbService;
    private SessionData data;

    @Autowired
    public LoginController(DbService dbService, SessionData data) {
        this.dbService = dbService;
        this.data = data;
    }

    @RequestMapping(value = "/loguser", method = RequestMethod.POST)
    public BasicResponse logUserIn(@RequestBody SimpleUser user, HttpSession session) {

        // TODO: 28.04.16 user agent?

        logger.info("Login request\t" + user.toString());
        User dbUser = null;

        if (session.getAttribute(session.getId()) != null) { // old session, user was successfully logged in
            try {
                logger.info("Session is still active.\n\t" + session.getAttribute(session.getId()).toString() +
                        "\n\tSession id: " + session.getId());
            } catch (Exception e) {
                logger.error("Session print exception. " + user.getEmail());
                return new BasicResponse("Session error");
            }
            SessionData data = (SessionData) session.getAttribute(session.getId());
            dbUser = dbService.findUserByEmail(data.getEmail());
            DataHolderResponse holder = generateHolder(dbUser);
            holder.setText("Session is still active.");
            return holder;
        }


        // new session
        try {
            dbUser = dbService.findUserByEmail(user.getEmail());
        } catch (Exception e) {
            //logger.error("Invalid e-mail pattern.");
            return new BasicResponse("Invalid e-mail pattern.");
        }
        if (dbUser != null) {
            if (dbUser.getPasswdAsString().equals(user.getPassword())) {
                logger.info("passwords ok!");

                // session bean
                data.setEmail(dbUser.getEmail());
                session.setAttribute(session.getId(), data);
                session.setMaxInactiveInterval(30 * 60); // 30min session timeout
                logger.info("Session info: " + data.toString());
                UUID uuid = UUID.randomUUID();
                data.setToken(uuid);
                DataHolderResponse response = this.generateHolder(dbUser);
                response.setToken(uuid.toString());
                return response;
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

    @RequestMapping(value = "/logout"   /*, method = RequestMethod.POST*/)
    public BasicResponse logoutUser(/*@RequestBody SimpleUser user,*/ HttpSession session) {
        BasicResponse response = new BasicResponse();
        logger.info("Logout request:\n\t" + session.getAttribute(session.getId()).toString());
        String id = session.getId();
        if (session.getAttribute(id) != null) { // still active
            session.invalidate();
            response.setResult();
            response.setText("You are logged out.");
            try {
                logger.info("After calling invalidate method:");
                logger.info(session.getAttribute(id).toString());
            } catch (Exception e) {
                logger.info("exception: could not get session attribute - session destroyed");
            }
        } else {
            response.setText("Session already expired");
        }
        logger.info("Returning: " + response.toString());
        return response;
    }

    private DataHolderResponse generateHolder(User dbUser) {
        DataHolderResponse holder = new DataHolderResponse();
        holder.setServices(dbUser.getSimpleServices());
        holder.setContractors(dbUser.getSimpleContractors());
        holder.setSalesmen(dbUser.getSimpleSalesmen());
        holder.setResult();
        holder.setText("You are logged in!");
        holder.checkNulls();
        return holder;
    }

}
