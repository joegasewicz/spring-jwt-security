package digital.josef.jwtsecurity;

import java.lang.annotation.*;

/**
 * Custom annotation to mark controller methods that require JWT authentication.
 *
 * <p>When a method is annotated with {@code @AuthRequired}, the {@link digital.josef.jwtsecurity.JwtAspect}
 * will intercept the call and verify that a valid JWT is present in the request's
 * Authorization header. If the token is missing or invalid, an HTTP 401 Unauthorized
 * response is returned.</p>
 *
 * <p>Usage example:</p>
 * <pre>
 * {@code
 * @AuthRequired
 * @GetMapping("/secure")
 * public ResponseEntity<String> secureEndpoint() {
 *     return ResponseEntity.ok("You are authenticated.");
 * }
 * }
 * </pre>
 *
 * @see digital.josef.jwtsecurity.JwtAspect
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthRequired {
}

