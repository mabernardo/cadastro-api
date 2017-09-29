package mabernardo.cadastroapi.web.rest.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * Classe auxiliar para cria&ccedil;&atilde;o de ResponseEntity.
 */
public final class ResponseUtil {

    private ResponseUtil() {
    }

    /**
     * Envelopa a opcional em uma {@link ResponseEntity} com um status {@link HttpStatus#OK}, ou se estiver vazia,
     * retorna uma {@link ResponseEntity} com status {@link HttpStatus#NOT_FOUND}.
     *
     * @param <X>           tipo da resposta
     * @param maybeResponse resposta a retornar se presente
     * @return resposta contendo {@code maybeResponse} se presente ou {@link HttpStatus#NOT_FOUND}
     */
    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse) {
        return wrapOrNotFound(maybeResponse, null);
    }

    /**
     * Envelopa a opcional em uma {@link ResponseEntity} com um status {@link HttpStatus#OK} com os cabe&ccedil;alhos,
     * ou se estiver vazia, retorna uma {@link ResponseEntity} com status {@link HttpStatus#NOT_FOUND}.
     *
     * @param <X>           tipo da resposta
     * @param maybeResponse resposta a retornar se presente
     * @param header        cabe&ccedil;alhos a serem adicionados &agrave; resposta
     * @return resposta contendo {@code maybeResponse} se presente ou {@link HttpStatus#NOT_FOUND}
     */
    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders header) {
        return maybeResponse.map(response -> ResponseEntity.ok().headers(header).body(response))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
