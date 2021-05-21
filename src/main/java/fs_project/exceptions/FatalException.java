package fs_project.exceptions;

/**
 * The type Fatal exception. Thrown in case of a fatal exception.
 */
public class FatalException extends CustomException {
    /**
     * Instantiates a new Fatal exception.
     *
     * @param statusCode the status code
     * @param errMessage the err message
     * @param err        the err
     */
    public FatalException(ResponseErrStatus statusCode, String errMessage, Throwable err) {
        super(statusCode, errMessage, err);
    }

    /**
     * Instantiates a new Fatal exception.
     *
     * @param statusCode the status code
     * @param errMessage the err message
     */
    public FatalException(ResponseErrStatus statusCode, String errMessage) {
        super(statusCode, errMessage);
    }
}
