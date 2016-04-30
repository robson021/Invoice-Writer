package robert.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import robert.other.SessionData;

/**
 * Created by robert on 30.04.16.
 */
@Configuration
public class OtherBeans {

    @Bean
    @Scope(value = "session")
    public SessionData sessionData() {
        System.out.println("session bean init");
        return new SessionData();
    }
}
