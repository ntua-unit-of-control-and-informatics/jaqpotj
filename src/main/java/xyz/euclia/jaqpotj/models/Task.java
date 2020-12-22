package xyz.euclia.jaqpotj.models;

/**
 *
 * @author Pantelispanka
 *
 */
public class Task extends JaqpotEntity{
    /**
     * Status of the task revealing information about the asynchronous job with
     * which the task is related and runs on a server.
     */
    public enum Status {

        /**
         * The task is still Running in the background as an asynchronous job.
         * This status means that the task has been submitted to the Execution
         * Pool but has not completed yet.
         */
        RUNNING,
        /**
         * The task has completed execution successfully. The result can be
         * found under #resultUri and is accessible via
         * {@link  #getResultUri()}. The corresponding status
         * is either 200 if the result is the URI of a created resource or 201
         * if it redirects to some other task (most probably on some other
         * server)
         */
        COMPLETED,
        /**
         * The task is canceled by the User.
         */
        CANCELLED,
        /**
         * Task execution was interrupted due to some error related with the
         * asynchronous job, either due to a bad request by the client, an
         * internal server error or an error related to a third remote service.
         * In such a case, the task is accompanied by an error report that
         * provides access to details about the exceptional event.
         */
        ERROR,
        /**
         * Due to large load on the server or issues related to A&amp;A or user
         * quota, the task was rejected for execution.
         */
        REJECTED,
        /**
         * The task is created and put in an execution queue but is not running
         * yet. HTTP status codes of queued tasks is 202.
         */
        QUEUED;
    }

    public enum Type {

        TRAINING,
        PREDICTION,
        PREPARATION,
        VALIDATION
    }

    private String _id;

    /**
     * Result URI.
     */
    private String resultUri;

    private String result;

    /**
     * Status of the feature.
     */
    private Task.Status hasStatus;
    /**
     * Percentage of completion.
     */
    private Float percentageCompleted;
    /**
     * Error report if the task has failed, or null.
     */
    private ErrorReport errorReport;
    /**
     * HTTP status code.
     */
    private Integer httpStatus;
    /**
     * Duration of the task when it has completed or has failed.
     */
    private Long duration;

    private Task.Type type;

    public Task() {
        super();
    }

    public Task(Task other) {
//        super(other);
        this.duration = other.duration;
        this.errorReport = other.errorReport != null ? new ErrorReport(other.errorReport) : null;
        this.hasStatus = other.hasStatus;
        this.httpStatus = other.httpStatus;
        this.percentageCompleted = other.percentageCompleted;
        this.result = other.result;
        this.type = other.type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Task(String id) {
        super(id);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultUri() {
        return resultUri;
    }

    public void setResultUri(String resultUri) {
        this.resultUri = resultUri;
    }


    public Task.Status getStatus() {
        return hasStatus;
    }

    public void setStatus(Task.Status hasStatus) {
        this.hasStatus = hasStatus;
    }

    public Float getPercentageCompleted() {
        return percentageCompleted;
    }

    public void setPercentageCompleted(Float percentageCompleted) {
        this.percentageCompleted = percentageCompleted;
    }

    public ErrorReport getErrorReport() {
        return errorReport;
    }

    public void setErrorReport(ErrorReport errorReport) {
        this.errorReport = errorReport;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Task.Type getType() {
        return type;
    }

    public void setType(Task.Type type) {
        this.type = type;
    }

}
