package mabernardo.cadastroapi.config;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe utilit&aacute;ria para carregar um profile default do Spring quando
 * o <code>spring.profiles.active</code> n&atilde;o est&aacute; definido no ambiente
 * nem na linha de comando.
 * Se o valor n&atilde;o estiver no <code>application.yml</code> ent&atilde;o o profile
 * <code>dev</code> ser&aacute; usado por default.
 */
public final class DefaultProfileUtil {

    private static final String SPRING_PROFILE_DEFAULT = "spring.profile.default";

    private DefaultProfileUtil() { }

    /**
     * Define um default quando n&atilde;o h&aacute; profile configurado.
     *
     * @param app a aplica&ccedil;&atilde;o Spring.
     */
    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defaultProps = new HashMap<>();
        defaultProps.put(SPRING_PROFILE_DEFAULT, Constantes.SPRING_PROFILE_DEV);
        app.setDefaultProperties(defaultProps);
    }

    /**
     * Obt&eacute;m os profiles que est&atilde;o em uso ou o profile default se n&atilde;o houver nenhum.
     *
     * @param env ambiente do Spring.
     * @return profiles.
     */
    public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (0 == profiles.length) {
            return env.getDefaultProfiles();
        }
        return profiles;
    }
}
