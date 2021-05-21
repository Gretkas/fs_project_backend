package fs_project.exceptions;

/**
 * The type Server error exception.
 */
public class ServerErrorException extends CustomException{
    /**
     * Instantiates a new Server error exception.
     *
     * @param statusCode the status code
     * @param errMessage the err message
     * @param err        the err
     */
    public ServerErrorException(ResponseErrStatus statusCode, String errMessage, Throwable err) {
        super(statusCode, errMessage, err);
    }

    /**
     * Instantiates a new Server error exception.
     *
     * @param statusCode the status code
     * @param errMessage the err message
     */
    public ServerErrorException(ResponseErrStatus statusCode, String errMessage) {
        super(statusCode, errMessage);
    }
}
