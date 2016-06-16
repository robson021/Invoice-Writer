package robert.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import robert.services.api.TokenService;
import robert.session.SessionData;

/**
 * Aspect that validates csrf token.
 * If token does not match the pattern user is redirected to login page.
 * Otherwise new token is generated and sent back.<br>
 * Created by robert on 06.06.16.
 */
@Aspect
public class CsrfAspect {
    private static final Logger logger = Logger.getLogger(CsrfAspect.class);

    @Autowired
    private TokenService tokenService;

    @After("execution(* robert.session.SessionData.setTokenToCheck(..))")
    public void validUuidCode(JoinPoint jp) {
        SessionData sessionData = null;
        String email = null;
        try {
            sessionData = (SessionData) jp.getTarget();
            email = sessionData.getEmail();
            logger.info("Validation of user: " + email);
            if (email == null || !tokenService.validateToken(sessionData.getToken(), sessionData.getTokenToCheck())) {
                sessionData.getResponse().sendRedirect("/");
            } else {
                sessionData.setToken(tokenService.generateNewToken());
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
