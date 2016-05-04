package robert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import robert.other.AboutAppInfo;
import robert.other.AboutAuthorInfo;
import robert.other.DefaultLogger;

/**
 * Created by robert on 04.05.16.
 */
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    DefaultLogger logger;

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
