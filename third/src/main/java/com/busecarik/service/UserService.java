package com.busecarik.service;


import com.busecarik.UserDto.UserDto;
import com.busecarik.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
    User findByEmail(String email);
    void save(UserDto user);
    List<User> listUsers();
}
