package mabernardo.cadastroapi.config;

/**
 * Constantes da aplica&ccedil;&atilde;o.
 *
 * @author Marcio Bernardo
 */
public final class Constantes {

    // Profiles do Spring para desenvolvimento, teste e produção.
    public static final String SPRING_PROFILE_DEV = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PROD = "prod";

    // Profile para desabilitar a execução do Liquibase
    public static final String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";

    // Spring Security
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    //Regex para logins válidos
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    private Constantes() { }

}
