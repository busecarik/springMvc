package com.busecarik.dao;

import com.busecarik.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findUserByUsername(String username) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User u where u.username =:username");
        query.setString("username", username);

        return (User) query.uniqueResult();

    }

    @Override
    public User findUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User u where u.email =:email");
        query.setString("email", email);

        return (User) query.uniqueResult();
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
}

    @Override
    public List<User> listUsers() {
        Session session = sessionFactory.getCurrentSession();
        return null;
    }
}
