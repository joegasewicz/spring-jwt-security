package digital.josef.jwtsecurity;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


/**
 * Aspect that intercepts controller methods annotated with {@link AuthRequired}
 * and checks for a valid JWT in the Authorization header.
 *
 * <p>This aspect runs before any method annotated with {@code @AuthRequired} and
 * ensures that a valid Bearer token is present and decodable. If valid, it sets
 * the decoded user ID as a request attribute named {@code "id"}.</p>
 *
 * <p>If the token is missing, malformed, or invalid, a {@link ResponseStatusException}
 * with status 401 (UNAUTHORIZED) is thrown.</p>
 */
@Aspect
@Component
public class JwtAspect {

    private final JwtService jwtService;
    private final HttpServletRequest request;

    /**
     * Constructor injection of the required services.
     *
     * @param jwtService the JWT service used to decode tokens
     * @param request the current HTTP request, used to extract headers
     */
    public  JwtAspect(JwtService jwtService, HttpServletRequest request) {
        this.jwtService = jwtService;
        this.request = request;
    }

     /**
     * Advice that runs before any method annotated with {@link AuthRequired}.
     * Extracts and validates the Bearer token from the Authorization header.
     *
     * @param joinPoint the join point providing reflection context (unused here)
     * @throws ResponseStatusException if the token is missing or invalid
     */
    @Before("@annotation(digital.josef.jwtsecurity.AuthRequired)")
    public void checkJwt(JoinPoint joinPoint) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        try {
            String id = jwtService.decodeToken(token);
            request.setAttribute("id", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }
}
