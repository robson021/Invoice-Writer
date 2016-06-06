package robert.services;

import org.springframework.beans.factory.annotation.Autowired;
import robert.services.api.FactoryUUID;
import robert.session.SessionData;

import java.util.UUID;

/**
 * Created by robert on 06.06.16.
 */
public class UuidFactoryImpl implements FactoryUUID {
    //private static final Logger logger = Logger.getLogger(FactoryUUID.class);

    @Autowired
    private SessionData sessionData;

    @Override
    public boolean checkToken(UUID uuid) {
        if (uuid.equals(sessionData.getUuid())) {
            return true;
        }
        return false;
    }

    @Override
    public UUID generateNewToken() {
        return UUID.randomUUID();
    }
}
