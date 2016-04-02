package robert.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import robert.entities.EmailAddress;
import robert.entities.User;
import robert.repositories.ContractorRepository;
import robert.repositories.SalesmanRepository;
import robert.repositories.ServiceRepository;
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
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ContractorRepository contractorRepository;
    @Autowired
    private SalesmanRepository salesmanRepository;

    @PostConstruct
    public void init() {
        logger.info("DbService constructed");

        User user = new User();
        user.setFirstName("Robert");
        user.setSurname("Nowak");
        char[] passwd = {'a', 'b', 'c'};
        user.setPassword(passwd);
        String email = "robert@example.mail.com";
        user.setEmail(new EmailAddress(email));

        userRepository.save(user);

        email = "robertn@example.mail.org";
        user.setEmail(new EmailAddress(email));
        userRepository.save(user);

        User user1 = new User();
        user1.setFirstName("John");
        user1.setSurname("Zorn");
        passwd = new char[]{'x', 'y', 'z'};
        user1.setPassword(passwd);
        user1.setEmail(new EmailAddress("john_zorn@example.mail.com"));

        userRepository.save(user1);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(new EmailAddress(email));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


}
