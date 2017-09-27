package mabernardo.cadastroapi.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.util.Arrays;

import mabernardo.cadastroapi.config.Constantes;

/**
 * Aspecto para logar a execu&ccedil;&atilde;o dos reposit&oacute;rios e servi&ccedil;os do Spring.
 *
 * Por padr&atilde;o, habilitado apenas no profile de "dev".
 */
@Aspect
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Environment env;

    public LoggingAspect(Environment env) {
        this.env = env;
    }

    /**
     * Pointcut para todos reposit&oacute;rios, servi&ccedil;os e endpoints REST.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Método vazio por tratar-se apenas de um Pointcut, a implementação fica nos Advices.
    }

    /**
     * Pointcut para todos beans do Spring nas packages principais da aplica&ccedil;&atilde;o.
     */
    @Pointcut("within(mabernardo.cadastroapi.repository..*)"+
            " || within(mabernardo.cadastroapi.service..*)"+
            " || within(mabernardo.cadastroapi.web.rest..*)")
    public void applicationPackagePointcut() {
        // Método vazio por tratar-se apenas de um Pointcut, a implementação fica nos Advices.
    }

    /**
     * Advice que loga m&eacute;todos disparando exceptions.
     *
     * @param joinPoint join point para o advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        if (env.acceptsProfiles(Constantes.SPRING_PROFILE_DEV)) {
            log.error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), e.getCause() != null? e.getCause() : "NULL", e.getMessage(), e);

        } else {
            log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), e.getCause() != null? e.getCause() : "NULL");
        }
    }

    /**
     * Advice que loga a entrada e sa&iacute;da de um m&eacute;todo.
     *
     * @param joinPoint join point para o advice
     * @return resultado
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

            throw e;
        }
    }
}
