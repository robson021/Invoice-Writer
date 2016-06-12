package robert.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import robert.services.api.UuidGenerator;
import robert.session.SessionData;

/**
 * Created by robert on 06.06.16.
 */
@Aspect
public class CsrfAspect {
    private static final Logger logger = Logger.getLogger(CsrfAspect.class);

    @Autowired
    private UuidGenerator uuidGenerator;

    // TODO: 06.06.16 methods

    @After("execution(* robert.session.SessionData.setUuidToCheck(..))")
    public void validUuidCode(JoinPoint jp) {
        try {
            SessionData sessionData = (SessionData) jp.getTarget();
            sessionData.getResponse().sendRedirect("/");
        } catch (Exception e) {
        }
    }
}
