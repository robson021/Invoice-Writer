package robert.services;

import org.apache.log4j.Logger;
import robert.services.api.UuidGenerator;

import java.util.UUID;

/**
 * Created by robert on 06.06.16.
 */
public class UuidFactoryImpl implements UuidGenerator {
    private static final Logger logger = Logger.getLogger(UuidGenerator.class);

    @Override
    public UUID generateNewToken() {
        UUID uuid = UUID.randomUUID();
        logger.info("New token generated: " + uuid.toString());
        return uuid;
    }

}
