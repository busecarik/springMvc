package com.busecarik.service;

import com.busecarik.UserDto.UserDto;
import com.busecarik.dao.UserDao;
import com.busecarik.model.Authorities;
import com.busecarik.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserDetailsServiceImpl implements UserService {

    private final UserDao userDao;

    private Logger logger = Logger.getLogger(getClass().getName());

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        User user = userDao.findUserByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getAuthorities());

        return buildUserForAuthentication(user, authorities);

    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Authorities> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (Authorities userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getAuthority()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

    @Override
    @Transactional
    public User findByUserName(String userName) {
        return userDao.findUserByUsername(userName);
    }

    @Transactional
    @Override
    public User findByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Transactional
    @Override
    public void save(UserDto userDto) {
        Set<Authorities> authorities = new HashSet<>();
        String sex = userDto.getSex();

        User user = new User(userDto.getUsername(),
                "{noop}" +userDto.getPassword(),
                userDto.getBirthday(),
                userDto.getEmail(),
                (sex.equals("female")) ? 1 : 0,
                true
        );
        authorities.add(new Authorities("ROLE_user", user));
        user.setAuthorities(authorities);
        userDao.save(user);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
