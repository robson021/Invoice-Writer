package robert.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by robert on 28.03.16.
 */
@Configuration
@EnableJpaRepositories
@Import(InfrastructureConfig.class)
public class JpaConfiguration {
}
