package com.busecarik.service;

import com.busecarik.dao.UserDao;
import com.busecarik.model.Authorities;
import com.busecarik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private Logger logger = Logger.getLogger(getClass().getName());

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

        // Build user's authorities
        for (Authorities userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getAuthority()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }


    @Override
    public void save(User user) {
        User newUser = new User();

    }
}
