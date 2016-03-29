package robert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import robert.entities.EmailAddress;
import robert.entities.User;
import robert.repositories.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InvoiceWriterApplication.class)
@WebAppConfiguration
public class InvoiceWriterApplicationTests {


    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void dbTests() {
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


        //TODO fix findByEmail method

        /*User dbUser2 = userRepository.findByEmail(new EmailAddress(email));
        assertNotNull(dbUser2);
        System.out.println(dbUser2.toString());
        */

        System.out.println("DB test complete");


    }

}
