package com.busecarik.controller;

import com.busecarik.UserDto.UserDto;
import com.busecarik.model.User;
import com.busecarik.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegisterPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registration";
    }

    @RequestMapping(value = "postRegistration", method = RequestMethod.POST)
    public String addNewUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult theBindingResult, Model theModel) {

        if (theBindingResult.hasErrors()){
            theModel.addAttribute("userDto", new UserDto());
            theModel.addAttribute("passwordError", "Passwords are not matched!");
            return "registration";
        }

        User existUsername = userService.findByUserName(userDto.getUsername());
        User existEmail = userService.findByEmail(userDto.getEmail());

        if(existUsername != null || existEmail != null) {
            if (existUsername != null) {
                theModel.addAttribute("usernameError", "Username exists!");
            }

            if (existEmail != null) {
                theModel.addAttribute("emailError", "Email exists!");
            }
            theModel.addAttribute("userDto", new UserDto());
            return "registration";
        }

        userService.save(userDto);
        return "login";
    }
}
