package com.busecarik.config;

import com.busecarik.service.UserServiceImp;

import javax.xml.ws.Endpoint;

public class UserServicePublisher {

    public static void main(String[] arg) {
        Endpoint.publish("http://localhost:8080/userservice", new UserServiceImp());
    }
}
