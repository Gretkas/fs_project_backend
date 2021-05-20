package fs_project.exceptions;


public class UnauthorizedException extends CustomException {

    public UnauthorizedException(ResponseErrStatus statusCode, String errMessage, Throwable err) {
        super(statusCode, errMessage, err);
    }

    public UnauthorizedException(ResponseErrStatus statusCode, String errMessage) {
        super(statusCode, errMessage);
    }
}
