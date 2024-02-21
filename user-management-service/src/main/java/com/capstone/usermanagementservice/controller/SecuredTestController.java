package com.capstone.usermanagementservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredTestController {


    @GetMapping
    public String getHello() {
        return "testing";
    }
}
