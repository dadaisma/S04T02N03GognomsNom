package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.exceptiones;

import org.springframework.web.reactive.function.client.WebClientResponseException;

public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String message, WebClientResponseException ex) {
        super(message);
    }
}
