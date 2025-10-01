package com.example.oauthmock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

    @GetMapping("/api/secure-data")
    public String getSecureData() {
        return "This is secured data accessible with a valid access token!";
    }
}
