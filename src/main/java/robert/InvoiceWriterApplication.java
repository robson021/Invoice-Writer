package robert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableTransactionManagement
public class InvoiceWriterApplication {
    public static ApplicationContext ctx;
    public static void main(String[] args) {
        ctx = SpringApplication.run(InvoiceWriterApplication.class, args);
    }
}
