package robert.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import robert.other.AboutAppInfo;
import robert.other.AboutAuthorInfo;
import robert.other.SessionData;
import robert.responses.BasicResponse;
import robert.responses.simpleentities.DataHolderResponse;
import robert.services.DbService;

/**
 * Created by robert on 04.05.16.
 */
@RestController
@RequestMapping("/data")
public class DataController {

    private static final int MAX_FILE_SIZE = 150_000;
    private Logger logger = Logger.getLogger(DataController.class);
    private SessionData sessionData;
    private DbService dbService;

    @Autowired
    public DataController(SessionData sessionData, DbService dbService) {
        //this.logger = logger;
        this.sessionData = sessionData;
        this.dbService = dbService;
    }


    @RequestMapping(value = "/uplad/img", method = RequestMethod.POST)
    public ResponseEntity<?> upladImg(@RequestParam("name") String name,
                                      @RequestParam("file") MultipartFile file) {
        logger.info("\n\tFile uplad request: " + sessionData.toString() + " File name: " + file.getName() + " " + name);
        BasicResponse response = new BasicResponse();
        if (!file.isEmpty() && sessionData.getEmail() != null &&
                file.getSize() <= MAX_FILE_SIZE && dbService.updateUserImg(sessionData.getEmail(), file)) {
            response.setText("File was successfully uploaded");
            response.setResult(true);
            //servletResponse.sendRedirect("/");
        } else {
            response.setText("Could not upload the file");
            //servletResponse.sendRedirect("/#/upload");
        }
        logger.info("upload status: " + response.isResult());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/update-user-data", method = RequestMethod.POST)
    public ResponseEntity<?> updateUserData(@RequestBody DataHolderResponse dataHolder) {
        BasicResponse response = new BasicResponse();
        logger.info("update user's db request: " + sessionData.getEmail() + "\n\t" + dataHolder.toString());

        try {
            dbService.updateUserData(dataHolder, sessionData.getEmail());
            response.setText("Your database records have been updated!");
            response.setResult(true);
        } catch (Exception e) {
            logger.error("Error occoured while saving data");
            response.setText("Error");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping("/about/app")
    public ResponseEntity<?> getAboutAppInfo() {
        logger.info("About app get request.");
        return new ResponseEntity<>(new AboutAppInfo(), HttpStatus.OK);
    }

    @RequestMapping("/about/author")
    public ResponseEntity<?> getAboutAuthorInfo() {
        logger.info("About author get request.");
        return new ResponseEntity<>(new AboutAuthorInfo(), HttpStatus.OK);
    }
}