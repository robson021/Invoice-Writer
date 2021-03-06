package robert.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import robert.entities.*;
import robert.repositories.api.UserRepository;
import robert.responses.simpleentities.DataHolderResponse;
import robert.responses.simpleentities.SimpleContractor;
import robert.responses.simpleentities.SimpleSalesman;
import robert.responses.simpleentities.SimpleService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
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
        Contractor contractor1 = new Contractor("Mark", "Morgan", "Mark & Friends Inc.",
                "Wallstreet", "32", "00-001", "New York", nip1);
        String nip2 = "456927400";
        Contractor contractor2 = new Contractor("John", "Coltrane", null, null, null, null, null, nip2);
        List<Contractor> contractors = new ArrayList<>();
        contractors.add(contractor1);
        contractors.add(contractor2);

        user1.setContractors(contractors);

        List<Salesman> salesmens = new ArrayList<>();
        nip1 = "597632109";
        Salesman salesman1 = new Salesman("Rachel", "Goswell", nip1);
        salesman1.setBankAccNo("313139218302");
        salesman1.setBankName("PKO BP");
        salesman1.setPhoneNo("323-323-543");
        salesman1.setRegon("13134243");
        salesman1.setCity("Warsaw");
        salesman1.setCompanyName("Rachel Soft");
        salesman1.setHomeNo("32");
        salesman1.setStreetName("Woronicza");
        salesman1.setPostCode("55-555");
        salesman1.setUser(user1);
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
        exampleUserId = user1.getId();
        exampleUserEmail = user1.getEmail();

        passwd = new char[]{'a', 'a'};
        User user2 = new User("Aaa", "Bbb", new EmailAddress("aa@aa.pl"), passwd);
        this.saveUser(user2);

        passwd = new char[]{'b', 'b'};
        this.saveUser(new User("Bbb", "Aaa", new EmailAddress("bb@bb.pl"), passwd));


        // PIZZA
        passwd = new char[]{'p', 'p'};
        User pizzaMan = new User("Robert", "Nowak", new EmailAddress("invoice.writer.app@gmail.com"), passwd);
        Salesman salesman = new Salesman("Robert", "Nowak", "543535");
        salesman.setBankName("Roosevelt Savings Bank");
        salesman.setBankAccNo("9837139163183685");
        salesman.setPhoneNo("323456123");
        salesman.setRegon("987386326");
        salesman.setPostCode("55-555");
        salesman.setCompanyName("Joey Pepproni Pizza");
        salesman.setStreetName("Broadway");
        salesman.setHomeNo("43");
        salesman.setCity("New York");

        pizzaMan.addSalesman(salesman);

        Contractor contractor = new Contractor("Jane", "Doe", "N/A", "Fulton Street", "21", "33-333", "New York", "443255664");
        pizzaMan.addContractor(contractor);

        TheService service = new TheService("Hawaiian pizza", "cheese, tomato, pieces of ham, pineapple, bacon", 8, 20);
        pizzaMan.addService(service);
        pizzaMan.addService(new TheService("Vege pizza", "cheese, onion, tomato, mushroom, cucumber, garlic, corn", 8, 18));

        saveUser(pizzaMan);
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

    public boolean updateUserImg(String email, MultipartFile file) {
        User user = userRepository.findByEmail(new EmailAddress(email));
        if (user == null) return false;


        byte[] bFile;
        try {
            bFile = file.getBytes();
        } catch (IOException e) {
            logger.error("getBytes() exception");
            return false;
        }
        boolean result = false;

        try {
            user.setImage(bFile);
            if (userRepository.save(user) != null) {
                result = true; // successfull save
            }
        } catch (Exception e) {
            logger.error("Exception - file update attempt of user: " + email);
        }
        return result;
    }

    public boolean updateUserData(DataHolderResponse dataHolder, String email) {
        User user = findUserByEmail(email);
        if (user == null) {
            logger.error("Can not find user");
            return false;
        }


        updateData(dataHolder, user);
        saveUser(user);
        return true;
    }

    public void updateData(DataHolderResponse dataHolder, User user) {
        List<TheService> newServices = new ArrayList<>();
        for (SimpleService s : dataHolder.getServices()) {
            newServices.add(new TheService(s));
        }
        List<Contractor> newContractors = new ArrayList<>();
        for (SimpleContractor c : dataHolder.getContractors()) {
            newContractors.add(new Contractor(c));
        }
        List<Salesman> newSalesmen = new ArrayList<>();
        for (SimpleSalesman s : dataHolder.getSalesmen()) {
            newSalesmen.add(new Salesman(s));
        }

        user.getContractors().clear();
        user.getSalesmen().clear();
        user.getServices().clear();
        logger.info("clear done");
        user.getContractors().addAll(newContractors);
        user.getServices().addAll(newServices);
        user.getSalesmen().addAll(newSalesmen);
    }


    public byte[] getUserImage(String email) {
        byte[] bytes = null;
        try {
            bytes = findUserByEmail(email).getImage();
        } finally {
            return bytes;
        }
    }
}