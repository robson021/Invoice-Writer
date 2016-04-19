package robert.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import robert.entities.User;
import robert.responses.simpleentities.DataHolderResponse;
import robert.responses.simpleentities.SimpleUser;
import robert.services.DbService;

/**
 * Created by robert on 09.04.16.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private DbService dbService;


    @RequestMapping("/")
    public ResponseEntity<DataHolderResponse> logUserIn(@RequestBody SimpleUser user) {
        User dbUser = null;
        DataHolderResponse holder = new DataHolderResponse();
        dbUser = dbService.findUserByEmail(user.getEmail());
        if (dbUser != null && user.getPassword().equals(dbUser.getPassword())) {
            holder.setServices(dbUser.getSimpleServices());
            holder.setContractors(dbUser.getSimpleContractors());
            holder.setSalesmen(dbUser.getSimpleSalesmen());
            holder.setResult(true);
        } else {
            logger.info("Invalid data. User not found.");
        }

        return new ResponseEntity<DataHolderResponse>(holder, HttpStatus.OK);
    }

}
