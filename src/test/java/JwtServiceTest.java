import digital.josef.jwtsecurity.JwtProperties;
import digital.josef.jwtsecurity.JwtService;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        JwtProperties props = new JwtProperties();
        props.setSecret("testtesttesttesttesttesttesttest");
        jwtService = new JwtService(props);
    }

    @Test
    void testGenerateAndDecodeToken() {
        String subject = "user123";
        Map<String, Object> claims = JwtService.generateClaims(subject);

        String token = jwtService.generateToken(claims, subject);
        assertNotNull(token);

        String decodedSubject = jwtService.decodeToken(token);
        assertEquals(subject, decodedSubject);
    }

    @Test
    void testDecodeInvalidToken() {
        String fakeToken = "invalid.token.value";
        assertThrows(JwtException.class, () -> jwtService.decodeToken(fakeToken));
    }
}
