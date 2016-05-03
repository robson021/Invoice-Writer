package robert.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import robert.other.DefaultLogger;

/**
 * Created by robert on 03.05.16.
 */

@Service
public class DefaultLoggerImpl implements DefaultLogger {
    private static final Logger logger = Logger.getLogger(DefaultLogger.class);

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }
}
