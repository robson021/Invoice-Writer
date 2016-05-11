package robert.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import robert.other.DefaultLogger;
import robert.other.SessionData;

/**
 * Created by robert on 30.04.16.
 */
@Configuration
public class OtherBeans {

    @Autowired
    private DefaultLogger logger;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SessionData sessionData() {
        logger.info("Session bean init");
        return new SessionData();
    }
}
