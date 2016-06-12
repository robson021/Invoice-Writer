package robert.services;

import robert.services.api.UuidGenerator;

import java.util.UUID;

/**
 * Created by robert on 06.06.16.
 */
public class UuidFactoryImpl implements UuidGenerator {
    //private static final Logger logger = Logger.getLogger(UuidGenerator.class);

    @Override
    public boolean checkToken(String uuid) {
        return false;
    }

    @Override
    public UUID generateNewToken() {
        return null;
    }

}
