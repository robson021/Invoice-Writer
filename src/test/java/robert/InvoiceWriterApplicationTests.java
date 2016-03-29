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


    @Test
    public void contextLoads() {
    }

    @Test
    public void dbTests() {
        System.out.println("DB test start");

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

        userRepository.save(user);
        serviceRepository.save(service);

        dbUser = null;
        dbUser = userRepository.findOne(user.getId());
        assertNotNull(dbUser);


        Contractor contractor = new Contractor();
        contractor.setName("John");
        contractor.setSurname("Smith");
        String nip = "938797387";
        contractor.setNipNo(nip);


        contractorRepository.save(contractor);

        Contractor dbContractor = contractorRepository.findOneByNipNo(nip);
        assertNotNull(dbContractor);
        System.out.println(dbContractor.toString());

        Salesman salesman = new Salesman();
        salesman.setName("Jaden");
        salesman.setSurname("Pitt");
        String nip2 = "563900582";
        salesman.setNipNo(nip2);

        salesmanRepository.save(salesman);

        Salesman dBsalesman = salesmanRepository.findOneByNipNo(nip2);
        assertNotNull(dBsalesman);
        System.out.println(dBsalesman.toString());


        //TODO fix relations
       /* System.out.println("User's services list contains:");
        for (TheService s : dbUser.getServices()) {
            System.out.println(s.toString());
        }*/



        //TODO fix findByEmail method

        /*User dbUser2 = userRepository.findByEmail(new EmailAddress(email));
        assertNotNull(dbUser2);
        System.out.println(dbUser2.toString());
        */

        System.out.println("DB test finish");


    }

}
