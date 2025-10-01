package com.example.oauthmock.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TokenService {

    private static final Map<String, String> CLIENTS = Map.of(
            "my-client", "my-secret"
    );

    private final Map<String, String> tokens = new HashMap<>();

    public boolean validateClient(String clientId, String clientSecret) {
        return CLIENTS.containsKey(clientId) && CLIENTS.get(clientId).equals(clientSecret);
    }

    public String generateToken(String clientId) {
        String token = UUID.randomUUID().toString();
        tokens.put(token, clientId);
        return token;
    }

    public boolean validateToken(String token) {
        return tokens.containsKey(token);
    }
}
