package mabernardo.cadastroapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;
import javax.annotation.PostConstruct;


@SpringBootApplication
public class CadastroApiApplication {

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("utc"));
	}

	public static void main(String[] args) {
		SpringApplication.run(CadastroApiApplication.class, args);
	}
}
