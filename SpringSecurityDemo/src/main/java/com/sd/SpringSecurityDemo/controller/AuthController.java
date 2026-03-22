package com.sd.SpringSecurityDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/showLoginPage")
    public String showLoginPage(){
        return "myLogin";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "accessDenied";
    }



}
