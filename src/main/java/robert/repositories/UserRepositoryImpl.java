package robert.repositories;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import robert.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by robert on 28.03.16.
 */

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = Logger.getLogger(UserRepositoryImpl.class);


    @PersistenceContext //@Autowired
    private EntityManager em;

    @Override
    public User findOne(Long id) {
        return null;
    }

    @Override
    public User save(User user) {
        if (em == null) {
            logger.error("ENTITY MANAGER == NULL");
            return null;
        } else logger.info("Entity manager: " + em.toString());
        if (user.getId() == null) {
            em.persist(user);
            logger.info("User has been added.");
            return user;
        } else {
            logger.info("save via merge");
            return em.merge(user);
        }
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
