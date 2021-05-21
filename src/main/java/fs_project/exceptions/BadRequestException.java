package fs_project.exceptions;

/**
 * The type Bad request exception. Thrown in case of a request with missing or invalid data.
 */
public class BadRequestException extends CustomException {

    /**
     * Instantiates a new Bad request exception.
     *
     * @param statusCode the status code
     * @param errMessage the err message
     * @param err        the err
     */
    public BadRequestException(ResponseErrStatus statusCode, String errMessage, Throwable err) {
        super(statusCode, errMessage, err);
    }

    /**
     * Instantiates a new Bad request exception.
     *
     * @param statusCode the status code
     * @param errMessage the err message
     */
    public BadRequestException(ResponseErrStatus statusCode, String errMessage) {
        super(statusCode, errMessage);
    }
}
