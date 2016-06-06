package robert.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import robert.services.api.FactoryUUID;

/**
 * Created by robert on 06.06.16.
 */
@Aspect
public class ControllerAdvice {
    private static final Logger logger = Logger.getLogger(ControllerAdvice.class);

    @Autowired
    private FactoryUUID factoryUUID;


    // TODO: 06.06.16 methods
}
