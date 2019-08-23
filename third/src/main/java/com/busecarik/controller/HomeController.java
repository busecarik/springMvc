package com.busecarik.controller;

import com.busecarik.model.User;
import com.busecarik.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/list-users", method = RequestMethod.GET)
    public String showUsers(Model model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "list-users";
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public void printPage() {

    }
}
