package com.busecarik.service;

import com.busecarik.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserService {

    @WebMethod
    void addUser(User user);

    @WebMethod
    void deleteUser(String username);

    @WebMethod
    void updateUser(User user);

    @WebMethod
    User getUser(String username);
}
