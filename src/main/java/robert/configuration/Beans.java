package robert.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import robert.repositories.UserRepositoryImpl;
import robert.repositories.api.UserRepository;
import robert.services.InvoiceGeneratorImpl;
import robert.services.MailerImpl;
import robert.services.UuidFactoryImpl;
import robert.services.api.DefaultLogger;
import robert.services.api.FactoryUUID;
import robert.services.api.InvoiceGenerator;
import robert.services.api.Mailer;
import robert.session.SessionData;

/**
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
    public FactoryUUID factoryUUID() {
        return new UuidFactoryImpl();
    }
}
