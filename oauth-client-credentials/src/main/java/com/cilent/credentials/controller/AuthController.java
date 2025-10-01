package com.cilent.credentials.controller;

import com.cilent.credentials.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> getToken(
            @RequestParam String client_id,
            @RequestParam String client_secret
    ) {
        if (tokenService.validateClient(client_id, client_secret)) {
            String token = tokenService.generateToken(client_id);
            return ResponseEntity.ok(Map.of("access_token", token, "token_type", "bearer"));
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "invalid_client"));
        }
    }
}
