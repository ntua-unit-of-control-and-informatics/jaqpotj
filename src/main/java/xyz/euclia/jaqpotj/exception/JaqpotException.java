package xyz.euclia.jaqpotj.exception;

public class JaqpotException extends RuntimeException{

    private int status;

    public JaqpotException() {
    }

    public JaqpotException(String message) {
        super(message);
    }

    public JaqpotException(String message, Throwable cause) {
        super(message, cause);
    }

    public JaqpotException(Throwable cause) {
        super(cause);
    }

    public JaqpotException(int status, String message) {
        super(message);
        this.status = status;
    }

    public JaqpotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
