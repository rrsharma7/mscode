package com.behalf.of.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OBOService {

    private final Map<String, String> validClientTokens = Map.of("client-token-123", "clientA");
    private final Map<String, String> validUserTokens = Map.of("user-token-456", "userX");

    private final Map<String, String> oboTokens = new HashMap<>();

    public boolean validateClientToken(String token) {
        return validClientTokens.containsKey(token);
    }

    public boolean validateUserToken(String token) {
        return validUserTokens.containsKey(token);
    }

    public String generateOboToken(String clientToken, String userToken) {
        String combinedKey = validClientTokens.get(clientToken) + ":" + validUserTokens.get(userToken);
        String oboToken = UUID.randomUUID().toString();
        oboTokens.put(oboToken, combinedKey);
        return oboToken;
    }

    public boolean validateOboToken(String oboToken) {
        return oboTokens.containsKey(oboToken);
    }
}
