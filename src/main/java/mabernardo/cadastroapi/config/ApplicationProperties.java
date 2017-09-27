package mabernardo.cadastroapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Propriedades espec&iacute;ficas da aplica&ccedil;&atilde;o.
 *
 * Essas propriedades s&atilde;o configuradas no arquivo application.yml,
 * na se&ccedil;&atilde;o "application".
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

}
