package com.busecarik.dao;

import com.busecarik.model.User;

import java.util.List;

public interface UserDao {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    void save(User user);
    List<User> listUsers();
}
