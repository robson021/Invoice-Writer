package robert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class InvoiceWriterApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvoiceWriterApplication.class, args);
    }

    // TODO: 15.06.16 mysql or postresql DB 
}
