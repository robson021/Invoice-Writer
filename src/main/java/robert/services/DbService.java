package robert.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        logger.info("DbService constructed");

        User user = new User();
        user.setFirstName("Robert");
        user.setSurname("Nowak");
        char[] passwd = {'a', 'b', 'c'};
        user.setPassword(passwd);
        String email = "nowak_robert@example.mail.com";
        user.setEmail(email);

        userRepository.save(user);  //TODO fix this
    }


}
