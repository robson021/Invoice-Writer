package robert.repositories;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import robert.entities.EmailAddress;
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
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user.getId() == null) {
            em.persist(user);
            logger.info("User has been added");
            return user;
        } else {
            logger.info("save via merge");
            return em.merge(user);
        }
    }

    @Override
    @Transactional
    public User findByEmail(EmailAddress emailAddress) { //TODO write implementation for this method
        /*TypedQuery<User> query = em.createQuery("select u from User u where u.emailAddress = :email",
                User.class);
        query.setParameter("email", emailAddress);

        return query.getSingleResult();*/

        try {
            return (User) em.createQuery("SELECT c FROM User c WHERE c.emailAsString LIKE :email")
                    .setParameter("email", emailAddress.toString()).getSingleResult();
        } catch (Exception e) {
            logger.error("Finding user by email exception");
            e.printStackTrace();
            return null;
        }
    }

}
