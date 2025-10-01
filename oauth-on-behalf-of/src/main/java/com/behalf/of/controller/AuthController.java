package com.behalf.of.controller;

import com.behalf.of.service.OBOService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/obo")
public class AuthController {

    private final OBOService oboService;

    public AuthController(OBOService oboService) {
        this.oboService = oboService;
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> exchangeToken(
            @RequestParam String client_token,
            @RequestParam String user_token
    ) {
        if (oboService.validateClientToken(client_token) && oboService.validateUserToken(user_token)) {
            String oboToken = oboService.generateOboToken(client_token, user_token);
            return ResponseEntity.ok(Map.of("access_token", oboToken, "token_type", "bearer"));
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "invalid_client_or_user_token"));
        }
    }
}
