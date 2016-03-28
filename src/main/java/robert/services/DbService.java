package robert.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import robert.entities.EmailAddress;
import robert.entities.TheService;
import robert.entities.User;
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

    @PostConstruct
    public void init() {
        logger.info("DbService constructed");

        User user = new User();
        user.setFirstName("Robert");
        user.setSurname("Nowak");
        char[] passwd = {'a', 'b', 'c'};
        user.setPassword(passwd);
        String email = "nowak_robert@example.mail.com";
        user.setEmail(new EmailAddress(email));

        userRepository.save(user);

        email = "robertn@example.mail.org";
        user.setEmail(new EmailAddress(email));
        userRepository.save(user);

        String symbol = "E.S.";
        TheService service = new TheService("Example service", symbol, 23, 555.55);
        user.addService(service);
        TheService service2 = new TheService("Service #2", "S.no 2", 8, 33.62);
        user.addService(service2);
        serviceRepository.save(service);
        serviceRepository.save(service2);
        userRepository.save(user);

        User user2 = null;
        user2 = userRepository.findOne(user.getId());
        logger.info("Search test by id:\n" + user2.toString());

        System.out.println(user.getServices().toString());
        //System.out.println(user2.getServices().toString()); //TODO fix oneToMany relation
        TheService service3 = serviceRepository.findOneBySymbol(symbol);
        System.out.println(service3.toString());
    }


}
