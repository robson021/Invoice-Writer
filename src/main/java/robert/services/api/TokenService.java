package robert.services.api;

import java.util.UUID;

/**
 * Created by robert on 06.06.16.
 */
public interface TokenService {
    UUID generateNewToken();

    boolean validateToken(UUID pattern, UUID uuid);
}
