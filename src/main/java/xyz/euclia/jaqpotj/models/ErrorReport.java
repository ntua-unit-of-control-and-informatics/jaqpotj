package xyz.euclia.jaqpotj.models;

/**
 *
 * @author Pantelispanka
 *
 */
public class ErrorReport extends JaqpotEntity{

    /** Error code. */

    private String code;
    /**
     * Who is to blame.
     */
    private String actor;
    /**
     * Short error message;
     */
    private String message;
    /**
     * Details to be used for debugging.
     */
    private String details;
    /**
     * Accompanying HTTP status.
     */
    private int httpStatus = 0;
    /**
     * Trace error report.
     */

    public ErrorReport() {
    }

    public ErrorReport(String id) {
        super(id);
    }

    public ErrorReport(ErrorReport other) {
//        super(other);
        this.actor = other.actor;
        this.code = other.code;
        this.details = other.details;
        this.httpStatus = other.httpStatus;
        this.message = other.message;
//        this.trace = new ErrorReport(other.trace);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

}
