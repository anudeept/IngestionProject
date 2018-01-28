package com.iheartradio.IngestionProject.exception;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
public class TrackInternalServerException extends Exception {
    public TrackInternalServerException() {
        super();
    }

    public TrackInternalServerException(String message) {
        super(message);
    }

    public TrackInternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrackInternalServerException(Throwable cause) {
        super(cause);
    }

    public TrackInternalServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
