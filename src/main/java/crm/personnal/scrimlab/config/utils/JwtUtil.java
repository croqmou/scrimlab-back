package crm.personnal.scrimlab.config.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    public String jwtSecret;

    private SecretKey key;

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1h en ms

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(jwtSecret);
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        System.out.println("Clé JWT chargée : " + Base64.getEncoder().encodeToString(decodedKey));
    }


    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS384)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return extractAllClaims(token).getSubject();
        } catch (Exception e) {
            System.out.println("Erreur extraction username : " + e.getMessage());
            throw e;
        }
    }

    public boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration != null && expiration.before(new Date());
    }

    public boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(this.key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            // Log l’erreur ou gère la comme tu veux
            System.out.println("Token invalide ou expiré : " + e.getMessage());
            throw e;
        }
    }
}
