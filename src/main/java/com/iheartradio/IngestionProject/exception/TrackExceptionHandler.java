package com.iheartradio.IngestionProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
@ControllerAdvice
public class TrackExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(TrackException trackError) {
        return new ResponseEntity<>(trackError, trackError.getStatus());
    }

    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity<Object> resourceNotFound(TrackNotFoundException exception) {
        TrackException error = new TrackException(HttpStatus.NOT_FOUND);
        error.setMessage(exception.getMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(TrackInternalServerException.class)
    public ResponseEntity<Object> internalServerError(TrackInternalServerException exception) {
        TrackException error = new TrackException(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setMessage(exception.getMessage());
        return buildResponseEntity(error);
    }

}
