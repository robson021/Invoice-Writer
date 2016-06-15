package robert.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import robert.entities.Contractor;
import robert.entities.Salesman;
import robert.entities.TheService;
import robert.entities.User;
import robert.responses.Greetings;
import robert.responses.simpleentities.DataHolderResponse;
import robert.responses.simpleentities.SimpleContractor;
import robert.responses.simpleentities.SimpleSalesman;
import robert.responses.simpleentities.SimpleService;
import robert.services.DbService;
import robert.services.api.DefaultLogger;
import robert.session.SessionData;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by robert on 30.03.16.
 */
@RestController()
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DbService dbService;

    @Autowired
    private DefaultLogger logger;

    @Autowired
    SessionData sessionData;

    @RequestMapping(value = "/greetings", method = RequestMethod.GET)
    public List<Greetings> getGreetings() {
        logger.info("greetings test");
        Greetings greetings = new Greetings();
        List<Greetings> greetingsList = new ArrayList<>();
        greetingsList.add(greetings);
        Greetings greetings2 = new Greetings();
        greetings2.setText("greetings #2");
        Greetings greetings3 = new Greetings();
        greetings3.setText("Greetings #3");
        greetingsList.add(greetings2);
        greetingsList.add(greetings3);
        return greetingsList;
    }

    @RequestMapping(value = "/dataholder")
    public ResponseEntity<?> getExampleDataHolder() {
        logger.info("data holder get test");
        User user = dbService.findUserByEmail(DbService.getExampleUserEmail());

        List<SimpleContractor> simpleContractors = new ArrayList<>();
        List<SimpleSalesman> simpleSalesmen = new ArrayList<>();
        List<SimpleService> simpleServices = new ArrayList<>();

        for (Contractor c : user.getContractors()) {
            simpleContractors.add(c.getSimpleContractor());
        }
        for (Salesman s : user.getSalesmen()) {
            simpleSalesmen.add(s.getSimpleSalesman());
        }
        for (TheService se : user.getServices()) {
            simpleServices.add(se.getSimpleService());
        }

        DataHolderResponse holder = new DataHolderResponse();
        holder.setContractors(simpleContractors);
        holder.setSalesmen(simpleSalesmen);
        holder.setServices(simpleServices);

        return new ResponseEntity<>(holder, HttpStatus.OK);
    }

    @RequestMapping(value = "/dataupdate", method = RequestMethod.POST)
    public ResponseEntity<?> postDataToUpdate(@RequestBody DataHolderResponse holder) {
        logger.info(holder.toString());
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/get-image", method = RequestMethod.GET)
    public void getImageFile(HttpServletResponse response) {
        logger.info("Download file request: " + DbService.getExampleUserEmail());
        byte[] imageBytes = dbService.getUserImage(DbService.getExampleUserEmail());
        InputStream in = new ByteArrayInputStream(imageBytes);
        response.addHeader("Content-disposition", "attachment;filename="
                + "img");
        response.setContentType("txt/plain");

        try {
            IOUtils.copy(in, response.getOutputStream());
            logger.info("Ready to send");
            response.flushBuffer();
            logger.info("Sending done");
        } catch (Exception e) {
            logger.error("exception: IOUtils.copy(...)");
        }
    }

    @RequestMapping("/uuid-test")
    public void uuidTest(HttpServletResponse response) {
        UUID uuid = UUID.randomUUID();
        sessionData.setToken(uuid);
        sessionData.setTokenToCheck(uuid.toString(), response);
    }
}
