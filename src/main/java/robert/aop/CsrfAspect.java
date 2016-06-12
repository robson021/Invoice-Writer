package robert.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import robert.services.api.TokenService;
import robert.session.SessionData;

/**
 * Created by robert on 06.06.16.
 */
@Aspect
public class CsrfAspect {
    private static final Logger logger = Logger.getLogger(CsrfAspect.class);

    @Autowired
    private TokenService tokenService;

    @After("execution(* robert.session.SessionData.setUuidToCheck(..))")
    public void validUuidCode(JoinPoint jp) {
        SessionData sessionData = null;
        String email = null;
        try {
            sessionData = (SessionData) jp.getTarget();
            email = sessionData.getEmail();
            logger.info("Validation of user: " + email);
            if (email == null || !tokenService.validateToken(sessionData.getUuid(), sessionData.getUuidToCheck())) {
                sessionData.getResponse().sendRedirect("/");
            } else {
                sessionData.setUuid(tokenService.generateNewToken());
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

}
