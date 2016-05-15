package robert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import robert.other.AboutAppInfo;
import robert.other.AboutAuthorInfo;
import robert.other.DefaultLogger;
import robert.other.SessionData;
import robert.responses.BasicResponse;
import robert.services.DbService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by robert on 04.05.16.
 */
@RestController
@RequestMapping("/data")
public class DataController {

    private static final int MAX_FILE_SIZE = 150_000;
    private DefaultLogger logger;
    private SessionData data;
    private DbService dbService;

    @Autowired
    public DataController(DefaultLogger logger, SessionData data, DbService dbService) {
        this.logger = logger;
        this.data = data;
        this.dbService = dbService;
    }


    @RequestMapping(value = "/uplad/img", method = RequestMethod.POST)
    public ResponseEntity<?> upladImg(@RequestParam("name") String name,
                                      @RequestParam("file") MultipartFile file,
                                      HttpServletResponse servletResponse) throws IOException {
        logger.info("\n\tFile uplad request: " + data.toString() + " File name: " + file.getName() + " " + name);
        BasicResponse response = new BasicResponse();
        if (!file.isEmpty() && data.getEmail() != null &&
                file.getSize() <= MAX_FILE_SIZE && dbService.updateUserImg(data.getEmail(), file)) {
            response.setText("File was successfully uploaded");
            response.setResult(true);
            servletResponse.sendRedirect("/");
        } else {
            response.setText("Could not upload the file");
            //servletResponse.sendRedirect("/#/upload");
        }
        logger.info("upload status: " + response.isResult());
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
