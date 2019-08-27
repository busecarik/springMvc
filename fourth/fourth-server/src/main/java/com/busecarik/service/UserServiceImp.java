package com.busecarik.service;

import com.busecarik.dao.UserDao;
import com.busecarik.entity.User;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "UserService", endpointInterface = "com.busecarik.service.UserService")
public class UserServiceImp implements UserService{

    @Inject
    private UserDao userDao;

    @WebMethod
    public void addUser(@WebParam(name = "user") User user) {
        userDao.insert(user);
    }

    @WebMethod
    public void deleteUser(@WebParam(name = "username") String username) {
        userDao.delete(username);
    }

    @WebMethod
    public void updateUser(@WebParam(name = "user") User user) {
        userDao.update(user);
    }

    @WebMethod
    public User getUser(@WebParam(name = "username") String username) {
        return userDao.select(username);
    }

}
