package robert.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import robert.services.api.UuidGenerator;

/**
 * Created by robert on 06.06.16.
 */
@Aspect
@Component
public class CsrfAspect {
    private static final Logger logger = Logger.getLogger(CsrfAspect.class);

    @Autowired
    private UuidGenerator uuidGenerator;


    // TODO: 06.06.16 methods
}
