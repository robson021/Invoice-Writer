package robert.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by robert on 19.06.16.
 */
@Aspect
public class LoggerAspect {
    private static final Logger logger = Logger.getLogger(LoggerAspect.class);

    @AfterReturning(pointcut = "execution(* *(..)) && @annotation(robert.aop.AfterReturningMonitor)", returning = "retVal")
    private void returnedObjectInfo(JoinPoint joinPoint, Object retVal) throws Throwable {
        logger.info("After returning: " + joinPoint.getSignature().getName() +
                "\nReturned value: " + retVal.toString());
    }
}
