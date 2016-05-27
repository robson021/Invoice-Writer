package robert.controllers;

import org.apache.log4j.Logger;
import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import robert.other.SessionData;
import robert.responses.BasicResponse;
import robert.responses.InvoiceTemplate;
import robert.responses.simpleentities.DataHolderResponse;
import robert.services.DbService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by robert on 04.05.16.
 */
@RestController
@RequestMapping("/data")
public class DataController {

    private static final int MAX_FILE_SIZE = 150_000;
    private static final String IMAGE_NAME = "your_logo";
    private static final Logger logger = Logger.getLogger(DataController.class);
    private SessionData sessionData;
    private DbService dbService;

    @Autowired
    public DataController(SessionData sessionData, DbService dbService) {
        this.sessionData = sessionData;
        this.dbService = dbService;
    }


    @RequestMapping(value = "/uplad/img", method = RequestMethod.POST)
    public ResponseEntity<?> upladImg(/*@RequestParam("name") String name,*/
                                      @RequestParam("file") MultipartFile file) {
        logger.info("\n\tFile uplad request: " + sessionData.toString() + " File name: " + file.getName() /*+ " " + name*/);
        BasicResponse response = new BasicResponse();
        HttpStatus status = HttpStatus.OK;
        if (!file.isEmpty() && sessionData.getEmail() != null &&
                file.getSize() <= MAX_FILE_SIZE && dbService.updateUserImg(sessionData.getEmail(), file)) {
            response.setText("File has been successfully uploaded");
            response.setResult(true);
        } else {
            response.setText("Could not upload the file");
            status = HttpStatus.NOT_ACCEPTABLE;
        }
        logger.info("upload status: " + status);
        return new ResponseEntity<>(response, status);
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
            response.setText("Error - check for empty fields in data");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/get-image", method = RequestMethod.GET)
    public void getImageFile(HttpServletResponse response) {
        logger.info("Download file request: " + sessionData.getEmail());
        byte[] imageBytes = dbService.getUserImage(sessionData.getEmail());
        InputStream in = new ByteArrayInputStream(imageBytes);
        response.addHeader("Content-disposition", "attachment;filename="
                + IMAGE_NAME);
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

    @RequestMapping(value = "/submit-invoice", method = RequestMethod.POST)
    public ResponseEntity<?> submitInvoice(@RequestBody InvoiceTemplate invoiceTemplate) {
        BasicResponse response = new BasicResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}