package mabernardo.cadastroapi;

import mabernardo.cadastroapi.config.Constantes;
import mabernardo.cadastroapi.config.DefaultProfileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.TimeZone;
import javax.annotation.PostConstruct;


@SpringBootApplication
public class CadastroApiApplication {

    private static final Logger log = LoggerFactory.getLogger(CadastroApiApplication.class);

    private final Environment env;

    public CadastroApiApplication(Environment env) {
        this.env = env;
    }

    /**
     * Inicializa a aplica&ccedil;&atilde;o.
     * <p>Profiles do Spring podem ser configurados atrav&eacute;s do argumento --spring.profiles.active=profile</p>
     */
	@PostConstruct
	public void init() {
		Collection<String> activeProfiles = Arrays.asList(env.getDefaultProfiles());
		if (activeProfiles.contains(Constantes.SPRING_PROFILE_DEV)
                && activeProfiles.contains(Constantes.SPRING_PROFILE_PROD)) {
            log.error("A aplica\u00e7\u00e3o n\u00e3o pode rodar com profiles 'dev' e 'prod' ao mesmo tempo. " +
                    "Verifique os arquivos de configuração");
        }
        TimeZone.setDefault(TimeZone.getTimeZone("utc"));
	}

    /**
     * Ponto de entrada da aplica&ccedil;&atilde;o.
     *
     * @param args argumentos da linha de comando.
     * @throws UnknownHostException se n&atilde;o for poss&iacute;vel determinar o nome do localhost.
     */
	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(CadastroApiApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}\n\t" +
                "External: \t{}://{}:{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            protocol,
            env.getProperty("server.port"),
            protocol,
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"),
            env.getActiveProfiles());
	}
}
