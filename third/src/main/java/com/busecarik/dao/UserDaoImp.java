package com.busecarik.dao;

import com.busecarik.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserByUsername(String username) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User u where u.username =:username");
        query.setString("username", username);

        return (User) query.uniqueResult();

    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }
}
