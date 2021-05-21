package fs_project.exceptions;

/**
 * Superclass for custom exceptions.
 */
public abstract class CustomException extends RuntimeException{

    private final ResponseErrStatus statusCode;

    /**
     * Instantiates a new Custom exception.
     *
     * @param statusCode the status code
     * @param errMessage the err message
     * @param err        the err
     */
    public CustomException(ResponseErrStatus statusCode, String errMessage, Throwable err) {
        super(errMessage, err);
        this.statusCode = statusCode;
    }

    /**
     * Instantiates a new Custom exception.
     *
     * @param statusCode the status code
     * @param errMessage the err message
     */
    public CustomException(ResponseErrStatus statusCode, String errMessage) {
        super(errMessage);
        this.statusCode = statusCode;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    final public ResponseErrStatus getStatusCode() {
        return statusCode;
    }
}
