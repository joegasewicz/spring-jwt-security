package digital.josef.jwtsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Configuration class to enable support for handling components marked with AspectJ's {@code @Aspect} annotation.
 *
 * <p>This is required to make {@link digital.josef.jwtsecurity.JwtAspect} work properly for intercepting
 * method calls annotated with {@code @AuthRequired}.</p>
 *
 * <p>Spring AOP will automatically proxy the beans and apply the aspect logic before method execution.</p>
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
}
