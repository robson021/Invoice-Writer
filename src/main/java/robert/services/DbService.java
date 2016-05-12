package robert.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import robert.entities.*;
import robert.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 25.03.16.
 */

@Transactional
@Service
public class DbService {

    private static final Logger logger = Logger.getLogger(DbService.class);

    @Autowired
    private UserRepository userRepository;

   /* @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ContractorRepository contractorRepository;
    @Autowired
    private SalesmanRepository salesmanRepository;*/

    private static long exampleUserId;
    private static String exampleUserEmail;


    @PostConstruct
    public void init() {
        logger.info("DbService constructed");

        User user = new User();
        user.setFirstName("Example");
        user.setSurname("Man");
        char[] passwd = {'a', 'b', 'c'};
        user.setPassword(passwd);
        String email = "example@example.mail.com";
        user.setEmail(new EmailAddress(email));

        userRepository.save(user);

        // COMPLETE USER
        email = "test@t.pl";
        passwd = new char[]{'t', 't'};
        User user1 = new User("Robert", "Nowak", new EmailAddress(email), passwd);

        String nip1 = "123450987";
        Contractor contractor1 = new Contractor("Mark", "Morgan", null, null, null, null, null, nip1);
        String nip2 = "456927400";
        Contractor contractor2 = new Contractor("John", "Coltrane", null, null, null, null, null, nip2);
        List<Contractor> contractors = new ArrayList<>();
        contractors.add(contractor1);
        contractors.add(contractor2);

        user1.setContractors(contractors);

        List<Salesman> salesmens = new ArrayList<>();
        nip1 = "597632109";
        Salesman salesman1 = new Salesman("Rachel", "Goswell", nip1);
        nip2 = "974532999";
        Salesman salesman2 = new Salesman("Kevin", "Shields", nip2);
        salesmens.add(salesman1);
        salesmens.add(salesman2);

        user1.setSalesmen(salesmens);

        List<TheService> services = new ArrayList<>();
        String symbol1 = "gd43fd";
        TheService service1 = new TheService("Service_name", symbol1, 23, 863.75);
        String symbol2 = "jlke23dsa";
        TheService service2 = new TheService("Another_Service", symbol2, 8, 5673.43);

        services.add(service1);
        services.add(service2);

        user1.setServices(services);


        userRepository.save(user1);
        logger.info("Example user has been added.\n" + user1.toString());
        exampleUserId = user1.getId().longValue();
        exampleUserEmail = user1.getEmail();

        passwd = new char[]{'a', 'a'};
        User user2 = new User("Aaa", "Bbb", new EmailAddress("aa@aa.pl"), passwd);
        this.saveUser(user2);

        passwd = new char[]{'b', 'b'};
        this.saveUser(new User("Bbb", "Aaa", new EmailAddress("bb@bb.pl"), passwd));

    }

    public static long getExampleUserId() {
        return exampleUserId;
    }

    public static String getExampleUserEmail() {
        return exampleUserEmail;
    }

    public User findUserByEmail(EmailAddress email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(new EmailAddress(email));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }


}
