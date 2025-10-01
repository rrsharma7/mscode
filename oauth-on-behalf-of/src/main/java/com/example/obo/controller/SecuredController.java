package com.example.obo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

    @GetMapping("/api/obo-secure-data")
    public String getOboSecureData() {
        return "This is secured data accessible with a valid On-Behalf-Of token!";
    }
}
