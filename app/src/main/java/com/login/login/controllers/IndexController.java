package com.login.login.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "/")
    public String index(){
        return "views/index.html";
    }

    @GetMapping(value = "/signIn")
    public String signIn(){
        return "views/signIn.html";
    }
    
    @GetMapping(value = "/panel")
    public String panel(){
        return "views/panel.html";
    }
}
