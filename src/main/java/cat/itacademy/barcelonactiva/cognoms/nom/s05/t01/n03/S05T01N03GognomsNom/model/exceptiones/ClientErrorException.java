package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.exceptiones;

import org.springframework.web.reactive.function.client.WebClientResponseException;

public class ClientErrorException extends RuntimeException {
    public ClientErrorException(String message, WebClientResponseException ex) {
        super(message);
    }
}
