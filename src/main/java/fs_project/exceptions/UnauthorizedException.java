package fs_project.exceptions;


/**
 * The type Unauthorized exception. Is thrown if the user does not have access to the given resource.
 */
public class UnauthorizedException extends CustomException {

    /**
     * Instantiates a new Unauthorized exception.
     *
     * @param statusCode the status code
     * @param errMessage the err message
     * @param err        the err
     */
    public UnauthorizedException(ResponseErrStatus statusCode, String errMessage, Throwable err) {
        super(statusCode, errMessage, err);
    }

    /**
     * Instantiates a new Unauthorized exception.
     *
     * @param statusCode the status code
     * @param errMessage the err message
     */
    public UnauthorizedException(ResponseErrStatus statusCode, String errMessage) {
        super(statusCode, errMessage);
    }
}
