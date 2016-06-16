package robert.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import robert.aop.CsrfAspect;
import robert.repositories.UserRepositoryImpl;
import robert.repositories.api.UserRepository;
import robert.services.InvoiceGeneratorImpl;
import robert.services.MailerImpl;
import robert.services.TokenServiceImpl;
import robert.services.api.DefaultLogger;
import robert.services.api.InvoiceGenerator;
import robert.services.api.Mailer;
import robert.services.api.TokenService;
import robert.session.SessionData;

/**
 * Configuration class that is used to define
 * implementation of each bean's interface.<br>
 * Created by robert on 30.04.16.
 */
@Configuration
public class Beans {

    @Autowired
    private DefaultLogger logger;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SessionData sessionData() {
        logger.info("Session bean init");
        return new SessionData();
    }

    @Bean
    public Mailer mailer() {
        return new MailerImpl();
    }

    @Bean
    public InvoiceGenerator invoiceGenerator() {
        return new InvoiceGeneratorImpl();
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }

    @Bean
    public TokenService tokenService() {
        return new TokenServiceImpl();
    }

    @Bean
    public CsrfAspect csrfAspect() {
        return new CsrfAspect();
    }
}
