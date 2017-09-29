package mabernardo.cadastroapi.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Esta exception &eacute; disparada caso um usu&aacute;rio n&atilde;o ativado tente se autenticar.
 */
public class UserNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }
}
