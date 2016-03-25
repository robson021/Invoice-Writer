package robert.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import robert.entities.Contractor;
import robert.entities.User;
import robert.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

/**
 * Created by robert on 25.03.16.
 */

@Transactional
@Service
public class DbService {

    private static final Logger logger = Logger.getLogger(DbService.class);

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        User u = new User();

        String email = "nowakrobert@mymail.com";

        u.setFirstName("Robert");
        u.setSurname("Nowak");
        u.setEmail(email);

        char[] passwd = {'K', 'E', 'V', 'I', 'N'};
        u.setPassword(passwd);

        logger.info("Created new user: " + u.toString());

        Contractor contractor = new Contractor();
        contractor.setUser(u);
        contractor.setName("Jane");
        contractor.setSurname("Doe");

        userRepository.save(u);
        logger.info("User saved.");

        User u2 = null;
        u2 = userRepository.findByEmail(email);


        if (u2 != null) {
            logger.info("User has been found: " + u2.toString() /*  +". User's contractors: " + u2.getContractors().toString() */);
        } else {
            logger.error("User not found!");
        }

    }
}
