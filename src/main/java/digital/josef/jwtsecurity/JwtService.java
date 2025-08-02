package digital.josef.jwtsecurity;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Service class responsible for generating and verifying JWT tokens.
 * <p>
 * This class provides methods for token creation, validation, and claim generation.
 */
@Service
public class JwtService {

    private final JwtProperties jwtProperties;

    /**
     * Constructs a new JwtService with injected configuration properties.
     *
     * @param jwtProperties the configuration containing the secret key
     */
     public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * Returns the signing key based on the configured secret.
     *
     * @return the signing {@link Key}
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generates a JWT token with the given claims and subject.
     *
     * @param claims  a map of additional claims to include
     * @param subject the token's subject (e.g. user's email or ID)
     * @return a signed JWT token as a {@link String}
     */
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24hrs
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Decodes and verifies a JWT token, returning the subject (e.g. user ID or email).
     *
     * @param token the JWT token to verify
     * @return the subject stored in the token
     * @throws JwtException if the token is invalid or expired
     */
    public String decodeToken(String token) throws JwtException {
        Jws<Claims> parsed = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
        return parsed.getBody().getSubject(); // id
    }

    /**
     * Utility method for generating a simple claims map containing a user ID.
     *
     * @param id the user's ID
     * @return a {@link HashMap} with a single "id" claim
     */
    public static HashMap<String, Object> generateClaims(String id) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        return claims;
    }
}
