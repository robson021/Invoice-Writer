package robert.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import robert.repositories.UserRepositoryImpl;
import robert.repositories.api.UserRepository;

/**
 * Created by robert on 28.03.16.
 */
@Configuration
public class RepositoryBeans {

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }
}
