package robert.services;

import org.apache.log4j.Logger;
import robert.services.api.TokenService;

import java.util.UUID;

/**
 * Created by robert on 06.06.16.
 */
public class TokenServiceImpl implements TokenService {
    private static final Logger logger = Logger.getLogger(TokenService.class);

    @Override
    public UUID generateNewToken() {
        UUID uuid = UUID.randomUUID();
        logger.info("New token generated: " + uuid.toString());
        return uuid;
    }

    @Override
    public boolean validateToken(UUID pattern, UUID uuid) {
        if (pattern == null) {
            logger.error("Token = null. User is not logged in.");
            return false;
        }
        //logger.info("Given tokens:\n\t" + pattern.toString() + " and " + uuid.toString());
        if (pattern.equals(uuid)) {
            logger.info("OK. Tokens are equal");
            return true;
        } else {
            logger.error("Tokens do not match");
            return false;
        }
    }

}
