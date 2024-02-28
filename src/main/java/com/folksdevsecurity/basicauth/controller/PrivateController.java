package com.folksdevsecurity.basicauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class PrivateController {

    @GetMapping
    public String helloWord(){
        return "Hello word! From private endpoint";
    }

    //@PreAuthorize("USER")
    @GetMapping("/user")
    public String helloWorlPrivateUser(){
        return "Helo Private User";
    }

    //@PreAuthorize("ADMIN")
    @GetMapping("/admin")
    public String helloWorldPrivateAdmin(){
        return "Hello World Private Admin";
    }
}
