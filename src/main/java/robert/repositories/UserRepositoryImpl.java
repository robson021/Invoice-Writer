package robert.repositories;

import robert.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by robert on 28.03.16.
 */
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = em.createQuery("select c from User c where c.email = :email",
                User.class);
        query.setParameter("email", email);

        return query.getSingleResult();
    }
}
