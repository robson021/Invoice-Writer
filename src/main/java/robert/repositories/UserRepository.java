package robert.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robert.entities.User;

/**
 * Created by robert on 25.03.16.
 */


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
