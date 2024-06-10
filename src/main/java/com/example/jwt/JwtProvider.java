package com.example.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;

@Component
public class JwtProvider {

    private SecretKey cashedSecretKey;
    @Value("${custom.jwt.secretKey}")
    private String secretKeyPlain;

    private SecretKey _getSecretKey(){

        String KeyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());

        return Keys.hmacShaKeyFor(KeyBase64Encoded.getBytes());

    }
    public SecretKey getSecretKey(){
        if ( cashedSecretKey == null) cashedSecretKey = _getSecretKey();
        return cashedSecretKey;
    }
}
