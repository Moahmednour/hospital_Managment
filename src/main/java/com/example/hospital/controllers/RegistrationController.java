package com.example.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.hospital.entities.UserRegistrationDto;

import jakarta.validation.*;


@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        userService.registerNewUserAccount(userDto);
        return "redirect:/login";
    }
}
