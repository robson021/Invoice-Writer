package robert.repositories.api;

import org.springframework.data.repository.Repository;
import robert.entities.EmailAddress;
import robert.entities.User;

/**
 * Created by robert on 28.03.16.
 */
public interface UserRepository extends Repository<User, Long> {
    User findOne(Long id);

    User save(User user);

    User findByEmail(EmailAddress emailAddress);
}
