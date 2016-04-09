package robert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import robert.entities.*;
import robert.repositories.ContractorRepository;
import robert.repositories.SalesmanRepository;
import robert.repositories.ServiceRepository;
import robert.repositories.UserRepository;
import robert.services.DbService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InvoiceWriterApplication.class)
@WebAppConfiguration
public class InvoiceWriterApplicationTests {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private SalesmanRepository salesmanRepository;

    @Autowired
    private DbService dataBaseService;


    @Test
    public void contextLoads() {
    }

    @Test
    public void dbTests() {
        System.out.println("DB test start ******");

        String email = "test_email@example.com";
        User user = new User();
        user.setFirstName("Jane");
        user.setSurname("Doe");
        char[] passwd = {'a', 'b', 'c', 'd'};
        user.setPassword(passwd);
        user.setEmail(new EmailAddress(email));

        userRepository.save(user);

        //Long l = new Long(323232);
        User dbUser = userRepository.findOne(user.getId());
        assertNotNull(dbUser);
        assertEquals(email, dbUser.getEmail());
        System.out.println(dbUser.toString());

        TheService service = new TheService();
        service.setName("Test service");
        service.setNettoValue(567.43);
        service.setVatPercentage(23);
        service.calculatetVatAndBruttoValue();
        String symbol = "dsada-jneqwrt";
        service.setSymbol(symbol);

        serviceRepository.save(service);

        TheService dbService = serviceRepository.findOneBySymbol(symbol);
        assertNotNull(dbService);
        System.out.println(dbService.toString());


        // one to many relation check
        user.addService(service);
        service.setUser(user);
        Contractor c = new Contractor();
        c.setNipNo("111111111");
        user.addContractor(c);
        Salesman s = new Salesman();
        s.setNipNo("222222222");
        user.addSalesman(s);

        userRepository.save(user);
        salesmanRepository.save(s);
        contractorRepository.save(c);
        serviceRepository.save(service);

        dbUser = null;
        dbUser = userRepository.findOne(user.getId());
        assertNotNull(dbUser);

        System.out.println("user data from DB:");
        System.out.println(dbUser.toString());

        Contractor contractor = new Contractor();
        contractor.setName("Mark");
        contractor.setSurname("Smith");
        String nip = "938797387";
        contractor.setNipNo(nip);

        contractorRepository.save(contractor);

        Contractor dbContractor = contractorRepository.findOneByNipNo(nip);
        assertNotNull(dbContractor);
        System.out.println(dbContractor.toString());

        Salesman salesman = new Salesman();
        salesman.setName("Jaden");
        salesman.setSurname("Brown");
        String nip2 = "563900582";
        salesman.setNipNo(nip2);

        salesmanRepository.save(salesman);

        Salesman dBsalesman = salesmanRepository.findOneByNipNo(nip2);
        assertNotNull(dBsalesman);
        System.out.println(dBsalesman.toString());

        user.setFirstName("Xxxxx");
        user.setSurname("Yyyyy");

        userRepository.save(user);

        User someUser = userRepository.findOne(user.getId());
        System.out.println("User after data change:\n" + someUser.toString());


        // COMPLETE USER WITH ALL FIELDS
        email = "robert_nowak@gmail.com";
        passwd = new char[]{'p', 'a', 's', 's', 'w', 'd',};
        User user1 = new User("Robert", "Nowak", new EmailAddress(email), passwd);

        String nip1 = "123450987";
        Contractor contractor1 = new Contractor("Mark", "Morgan", null, null, null, null, null, nip1);
        nip2 = "456927400";
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

        user1.setSalesmens(salesmens);

        List<TheService> services = new ArrayList<>();
        String symbol1 = "gd43fd";
        TheService service1 = new TheService("Service_name", symbol1, 23, 863.75);
        String symbol2 = "jlke23dsa";
        TheService service2 = new TheService("Another_Service", symbol2, 8, 5673.43);

        services.add(service1);
        services.add(service2);

        user1.setServices(services);


        userRepository.save(user1);
        User testUser = null;
        testUser = dataBaseService.findUserById(user1.getId());
        assertNotNull(testUser);

        System.out.println(testUser.toString());


        testUser = null;
        testUser = dataBaseService.findUserByEmail(new EmailAddress(user1.getEmail()));
        assertNotNull(testUser);
        assertEquals(testUser, user1);
        assertEquals(testUser.toString(), user1.toString());

        /*System.out.println("User's services:");
        for (TheService theService : testUser.getServices()) {
            System.out.println(theService.toString());
        }*/

        System.out.println("DB test finish ******");
    }

}
