package robert.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import robert.services.api.FactoryUUID;

/**
 * Created by robert on 06.06.16.
 */
@Aspect
@Component
public class ControllerAspect {
    private static final Logger logger = Logger.getLogger(ControllerAspect.class);

    @Autowired
    private FactoryUUID factoryUUID;


    // TODO: 06.06.16 methods
}
