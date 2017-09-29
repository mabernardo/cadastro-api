package mabernardo.cadastroapi.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Classe auxiliar para cria&ccedil;&atilde;o de cabe&ccedil;alhos HTTP.
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-usersapiApp-alert", message);
        headers.add("X-usersapiApp-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert("Um(a) novo(a) {} foi criado(a) com identificador {}".format(entityName, param), param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert("O(A) {} com identificador {} foi atualizada".format(entityName, param), param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert("O(A) {} com identificador {} foi excluída".format(entityName, param), param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-usersapiApp-error", defaultMessage);
        headers.add("X-usersapiApp-params", entityName);
        return headers;
    }
}
