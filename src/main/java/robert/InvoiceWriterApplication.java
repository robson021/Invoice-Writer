package robert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableTransactionManagement
public class InvoiceWriterApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvoiceWriterApplication.class, args);
    }
}

// TODO: 09.04.16 fix json responses