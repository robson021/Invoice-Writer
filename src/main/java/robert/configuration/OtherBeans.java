package robert.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import robert.other.*;
import robert.repositories.FileRepository;
import robert.repositories.FileRepositoryImpl;

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

    @Bean
    public Mailer mailer() {
        return new MailerImpl();
    }

    @Bean
    public InvoiceGenerator invoiceGenerator() {
        return new InvoiceGeneratorImpl();
    }

    @Bean
    public FileRepository fileRepository() {
        return new FileRepositoryImpl();
    }
}
