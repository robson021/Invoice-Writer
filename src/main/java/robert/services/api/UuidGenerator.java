package robert.services.api;

import java.util.UUID;

/**
 * Created by robert on 06.06.16.
 */
public interface UuidGenerator {
    boolean checkToken(String uuid);
    UUID generateNewToken();
}