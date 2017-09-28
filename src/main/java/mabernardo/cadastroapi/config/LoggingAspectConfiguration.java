package mabernardo.cadastroapi.config;

import mabernardo.cadastroapi.aop.logging.LoggingAspect;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    @Profile(Constantes.SPRING_PROFILE_DEV)
    public LoggingAspect loggingAspect(Environment env) {
        return new LoggingAspect(env);
    }
}
