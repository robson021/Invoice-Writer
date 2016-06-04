package robert.controllers;

import com.itextpdf.text.Image;
import org.apache.log4j.Logger;
import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import robert.responses.BasicResponse;
import robert.responses.InvoiceTemplate;
import robert.responses.simpleentities.DataHolderResponse;
import robert.services.DbService;
import robert.services.api.InvoiceGenerator;
import robert.services.api.Mailer;
import robert.session.SessionData;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;

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
    private Mailer mailer;
    private InvoiceGenerator invoiceGenerator;

    @Autowired
    public DataController(SessionData sessionData, DbService dbService, Mailer mailer, InvoiceGenerator invoiceGenerator) {
        this.sessionData = sessionData;
        this.dbService = dbService;
        this.mailer = mailer;
        this.invoiceGenerator = invoiceGenerator;
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
        logger.info("Invoice submit from: " + sessionData.getEmail());
        BasicResponse r = new BasicResponse();
        if (!invoiceTemplate.validate()) {
            r.setText("Error - some fields are missing.");
        } else {
            Image image;
            try {
                image = Image.getInstance(dbService.getUserImage(sessionData.getEmail()));
            } catch (Exception e) {
                logger.error("Image load error");
                image = null;
            }
            String docName = invoiceGenerator.generateInvoice(invoiceTemplate, image);
            if (docName == null) {
                r.setText("Error - could not generate invoice file.");
            } else {
                try {
                    sessionData.setLastInvoice(docName);
                    r.setText("Invoice is ready. AdBlock may interrupt the download!");
                    r.setResult(true);
                    if (invoiceTemplate.isCopyOnMail()) {
                        sessionData.setMailerThread(mailer.sendInvoice(sessionData.getEmail(), docName));
                    } else {
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    r.setText(e.getMessage());
                    sessionData.clean();
                }
            }
        }
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @RequestMapping(value = "/download-invoice", method = RequestMethod.GET)
    public void downloadInvoice(HttpServletResponse response) {
        // TODO: 01.06.16 download NOT in new tab
        logger.info("Download request: " + sessionData.getEmail());
        try {
            response.addHeader("Content-disposition", "attachment;filename="
                    + "Invoice " + Calendar.getInstance().getTime().toString() + ".pdf");
            response.setContentType("txt/plain");
            InputStream is = new FileInputStream(sessionData.getLastInvoice());
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            logger.info(sessionData.getEmail() + " downloaded the file");
        } catch (Exception e) {
            logger.error("Could not download");
        } finally {
            sessionData.clean();
        }
    }

}