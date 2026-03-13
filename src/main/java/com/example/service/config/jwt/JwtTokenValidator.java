package com.example.service.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class JwtTokenValidator {

    private final RSAPublicKey publicKey;

    public JwtTokenValidator(@Value("${jwt.public-key-path}") String publicKeyPath) {
        try {
            this.publicKey = loadPublicKey(publicKeyPath);
            System.out.println("✅ JwtTokenValidator 초기화 성공: " + publicKeyPath);
        } catch (Exception e) {
            System.out.println("❌ JwtTokenValidator 초기화 실패: " + e.getMessage());
            throw new RuntimeException("JWT public key 로드 실패", e);
        }
    }

    private RSAPublicKey loadPublicKey(String path) throws Exception {
        String key = new String(Files.readAllBytes(Paths.get(path)))
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(spec);
    }

    public Claims validate(String token) {
        return Jwts.parser()                    // parserBuilder() → parser()
                .verifyWith(publicKey)          // setSigningKey() → verifyWith()
                .build()
                .parseSignedClaims(token)       // parseClaimsJws() → parseSignedClaims()
                .getPayload();                  // getBody() → getPayload()
    }
}
