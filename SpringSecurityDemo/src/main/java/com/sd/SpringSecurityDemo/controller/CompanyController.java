package com.sd.SpringSecurityDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CompanyController {

    @GetMapping("/")
    public String appRoot(){
        return "home";
    }

    @GetMapping("/page/home")
    public String home(){
        return "home";
    }

    @GetMapping("/page/leaders")
    public String leaders(){
        return "leaders";
    }

    @GetMapping("/page/systems")
    public String systems(){
        return "systems";
    }

}
