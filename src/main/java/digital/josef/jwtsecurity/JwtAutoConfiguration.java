package digital.josef.jwtsecurity;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Auto-configuration class for JWT components.
 *
 * <p>This class automatically registers the {@link JwtProperties} and {@link JwtService}
 * as Spring beans when the library is included in a Spring Boot application.</p>
 *
 * <p>Make sure your consuming application includes the following in {@code application.properties}:</p>
 * <pre>
 * jwt.secret=your-256-bit-secret-key
 * </pre>
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAutoConfiguration {

    /**
     * Creates a singleton {@link JwtService} bean with configured properties.
     *
     * @param jwtProperties the properties loaded from configuration files
     * @return an instance of {@link JwtService}
     */
     @Bean
     public JwtService jwtService(JwtProperties jwtProperties) {
        return new JwtService(jwtProperties);
    }
}
