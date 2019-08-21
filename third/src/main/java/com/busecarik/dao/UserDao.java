package com.busecarik.dao;

import com.busecarik.model.User;

public interface UserDao {
    User findUserByUsername(String username);
    void save(User user);
}
