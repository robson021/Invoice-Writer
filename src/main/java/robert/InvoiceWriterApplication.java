package robert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class InvoiceWriterApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvoiceWriterApplication.class, args);
    }
}
