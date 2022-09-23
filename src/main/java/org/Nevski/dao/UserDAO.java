package org.Nevski.dao;

import org.Nevski.models.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Component
public class UserDAO {

//    private final SessionFactory sessionFactory;

//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<User> index() {
        List<User> users = entityManager.createQuery("select u from User u", User.class).getResultList();
        return users;
    }

    @Transactional(readOnly = true)
    public User show(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Transactional
    public void save(User person) {
        entityManager.persist(person);
    }

    @Transactional
    public void update(int id, User updatedPerson) {
        User user = entityManager.find(User.class, id);
        user.setAge(updatedPerson.getAge());
        user.setName(updatedPerson.getName());
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
