package fs_project.exceptions;

public class FatalException extends CustomException {
    public FatalException(ResponseErrStatus statusCode, String errMessage, Throwable err) {
        super(statusCode, errMessage, err);
    }

    public FatalException(ResponseErrStatus statusCode, String errMessage) {
        super(statusCode, errMessage);
    }
}
