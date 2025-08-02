package digital.josef.jwtsecurity;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties class for JWT settings.
 *
 * <p>This class binds to properties prefixed with {@code jwt} in your
 * application's {@code application.properties} or {@code application.yml} file.</p>
 *
 * <p>Example:
 * <pre>
 * jwt.secret=your-256-bit-secret-key
 * </pre>
 * </p>
 */
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
    * The secret key used for signing and verifying JWT tokens.
    */
    private String secret;

    /**
     * Returns the configured JWT secret.
     *
     * @return the secret string
     */
     public String getSecret() {
        return secret;
    }

    /**
     * Sets the JWT secret key.
     *
     * @param secret the secret string
     */
     public void setSecret(String secret) {
        this.secret = secret;
    }
}
