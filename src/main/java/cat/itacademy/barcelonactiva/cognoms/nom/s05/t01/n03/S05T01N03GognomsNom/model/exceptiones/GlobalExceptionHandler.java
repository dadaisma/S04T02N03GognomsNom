package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.exceptiones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidFlowerDataException.class)
    public ResponseEntity<ErrorMessage> handleInvalidFlowerDataException(InvalidFlowerDataException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }



        @ExceptionHandler(HttpClientErrorException.NotFound.class)
        public ResponseEntity<ErrorMessage> handleNotFoundException(HttpClientErrorException.NotFound ex, WebRequest request) {
            ErrorMessage message = new ErrorMessage(
                    HttpStatus.NOT_FOUND.value(),
                    new Date(),
                    ex.getMessage(),
                    request.getDescription(false));
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(HttpClientErrorException.class)
        public ResponseEntity<ErrorMessage> handleClientErrorException(HttpClientErrorException ex, WebRequest request) {
            ErrorMessage message = new ErrorMessage(
                    ex.getRawStatusCode(),
                    new Date(),
                    ex.getMessage(),
                    request.getDescription(false));
            return new ResponseEntity<>(message, ex.getStatusCode());
        }

        @ExceptionHandler(HttpServerErrorException.class)
        public ResponseEntity<ErrorMessage> handleServerErrorException(HttpServerErrorException ex, WebRequest request) {
            ErrorMessage message = new ErrorMessage(
                    ex.getRawStatusCode(),
                    new Date(),
                    ex.getMessage(),
                    request.getDescription(false));
            return new ResponseEntity<>(message, ex.getStatusCode());
        }



}
