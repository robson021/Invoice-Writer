package robert.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import robert.services.api.UuidGenerator;
import robert.session.SessionData;

import java.util.UUID;

/**
 * Created by robert on 06.06.16.
 */
@Aspect
public class CsrfAspect {
    private static final Logger logger = Logger.getLogger(CsrfAspect.class);

    @Autowired
    private UuidGenerator uuidGenerator;

    @After("execution(* robert.session.SessionData.setUuidToCheck(..))")
    public void validUuidCode(JoinPoint jp) {
        SessionData sessionData = (SessionData) jp.getTarget();
        String email = sessionData.getEmail();
        logger.info("Validation of user: " + email);
        try {
            if (!validateToken(sessionData.getUuid(), sessionData.getUuidToCheck())) {
                sessionData.getResponse().sendRedirect("/");
            } else {
                sessionData.setUuid(uuidGenerator.generateNewToken());
            }
        } catch (Exception e) {
            logger.error("Error");
            try {
                sessionData.getResponse().sendRedirect("/");
            } catch (Exception e1) {
                logger.error("Can not redirect to home page");
            }
        } finally {
            logger.info("Validation of " + email + " done");
        }
    }

    private boolean validateToken(UUID pattern, UUID uuid) {
        if (pattern == null) {
            logger.error("Token = null");
            return false;
        }
        logger.info("Given tokens:\n\t" + pattern.toString() + " and " + uuid.toString());
        if (pattern.equals(uuid)) {
            logger.info("OK. Tokens are equal");
            return true;
        } else {
            logger.error("Tokens do not match");
            return false;
        }
    }
}
