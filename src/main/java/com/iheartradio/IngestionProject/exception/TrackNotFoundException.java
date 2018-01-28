package com.iheartradio.IngestionProject.exception;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
public class TrackNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public TrackNotFoundException() {
        super();
    }

    public TrackNotFoundException(String message) {
        super(message);
    }

    public TrackNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrackNotFoundException(Throwable cause) {
        super(cause);
    }

    public TrackNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
