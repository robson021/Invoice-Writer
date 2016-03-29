package robert.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import robert.repositories.UserRepository;
import robert.repositories.UserRepositoryImpl;

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
