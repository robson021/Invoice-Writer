package robert;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import robert.entities.*;
import robert.repositories.api.ContractorRepository;
import robert.repositories.api.SalesmanRepository;
import robert.repositories.api.ServiceRepository;
import robert.repositories.api.UserRepository;
import robert.responses.simpleentities.*;
import robert.services.DbService;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InvoiceWriterApplication.class)
@WebAppConfiguration
public class InvoiceWriterApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;


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

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void dbTests() throws IOException {
        System.out.println("\nDB test start ******");

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

        // test on user that is created in DbService via @posconstruct method
        User testUser = null;
        testUser = dataBaseService.findUserById(DbService.getExampleUserId());
        assertNotNull(testUser);

        System.out.println(testUser.toString());


        testUser = null;
        testUser = dataBaseService.findUserByEmail(new EmailAddress(DbService.getExampleUserEmail()));
        assertNotNull(testUser);
        /*testUser = null;
        testUser = dataBaseService.findUserById(DbService.getExampleUserId());
        assertNotNull(testUser);*/
        //TODO finding user by id causes invalid lists sizes (duplicated records)
        assertEquals(testUser.getSalesmen().size(), 2);
        assertEquals(testUser.getContractors().size(), 2);
        assertEquals(testUser.getServices().size(), 2);
        System.out.println("sizes of lists: ");
        System.out.println(testUser.getSalesmen().size());
        System.out.println(testUser.getContractors().size());
        System.out.println(testUser.getServices().size());


        /*System.out.println("User's services:");
        for (TheService theService : testUser.getServices()) {
            System.out.println(theService.toString());
        }*/


        System.out.println("before encoding: " + testUser.getPassword());
        char[] p = testUser.encode(testUser.getPasswdAsString());
        System.out.println("encoded: " + p);

        String str = "";
        for (char c1 : p) {
            str += c1;
        }

        p = testUser.decode(str);
        System.out.println("decoded: " + p);

        str = "";
        for (char c1 : p) {
            str += c1;
        }

        System.out.println("oryginal: " + testUser.getPasswdAsString() + "\nencoded: " + str);

        assertEquals(testUser.getPasswdAsString(), str);


        dbUser = dataBaseService.findUserByEmail(DbService.getExampleUserEmail());
        assertNotNull(dbUser);

        String filename = "test_file";
        byte[] bFile = "test text".getBytes();
        MultipartFile multipartFile = new MockMultipartFile(filename, bFile);
        bFile = multipartFile.getBytes();
        System.out.println("File save test");
        dataBaseService.updateUserImg(dbUser.getEmail(), multipartFile);

        dbUser = dataBaseService.findUserByEmail(DbService.getExampleUserEmail());
        byte[] dbBytes = dbUser.getImage();


        String s1 = new String(bFile);
        String s2 = new String(dbBytes);

        System.out.println("String comparsion: " + "\n" + s1 + " - " + s2);

        assertEquals(s1, s2);

        dbUser.addContractor(new Contractor());
        int contractorsNum = dbUser.getContractors().size();
        dataBaseService.saveUser(dbUser);

        dbUser = dataBaseService.findUserByEmail(DbService.getExampleUserEmail());

        assertEquals(contractorsNum, dbUser.getContractors().size());

        List<SimpleContractor> simpleContractorList = dbUser.getSimpleContractors();
        List<SimpleService> simpleServices = dbUser.getSimpleServices();
        List<SimpleSalesman> simpleSalesmen = dbUser.getSimpleSalesmen();
        DataHolderResponse dataHolder = new DataHolderResponse();
        dataHolder.setContractors(simpleContractorList);
        dataHolder.setServices(simpleServices);
        dataHolder.setSalesmen(simpleSalesmen);

        dbUser.getContractors().clear();
        dbUser.getSalesmen().clear();
        dbUser.getServices().clear();

        dataBaseService.saveUser(dbUser);

        dbUser = dataBaseService.findUserByEmail(DbService.getExampleUserEmail());
        assertEquals(0, dbUser.getContractors().size());
        assertEquals(0, dbUser.getSalesmen().size());
        assertEquals(0, dbUser.getServices().size());

        dataBaseService.updateData(dataHolder, dbUser);

        dataBaseService.saveUser(dbUser);

        dbUser = dataBaseService.findUserByEmail(DbService.getExampleUserEmail());
        assertEquals(contractorsNum, dbUser.getContractors().size());
        System.out.println(dbUser);

        System.out.println("DB test finish ******");
    }

    @Test
    public void mvcTest() throws Exception {
        System.out.println("MVC TEST START *****************");

        User dbUser = dataBaseService.findUserByEmail(DbService.getExampleUserEmail());
        SimpleUser simpleUser = new SimpleUser(dbUser);

        mvc.perform(post("/login/loguser")
                .content(asJsonString(simpleUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


        DataHolderResponse dataHolder = new DataHolderResponse();
        dataHolder.setContractors(dbUser.getSimpleContractors());
        dataHolder.setSalesmen(dbUser.getSimpleSalesmen());
        dataHolder.setServices(dbUser.getSimpleServices());

        mvc.perform(post("/data/update-user-data")
                .content(asJsonString(dataHolder))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


        simpleUser.setEmail("testuseremail@gmail.com");
        simpleUser.setPassword("aaa");
        simpleUser.setRepassword("aaa");
        mvc.perform(post("/register/newuser")
                .content(asJsonString(simpleUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


        mvc.perform(get("/test/dataholder")).andExpect(status().isOk());

        //mvc.perform(get("/test/get-image"));

        System.out.println("MVC TEST FINISH ******************");
    }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
