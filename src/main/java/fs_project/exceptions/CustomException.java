package fs_project.exceptions;

public abstract class CustomException extends RuntimeException{

    private final ResponseErrStatus statusCode;

    public CustomException(ResponseErrStatus statusCode, String errMessage, Throwable err) {
        super(errMessage, err);
        this.statusCode = statusCode;
    }

    public CustomException(ResponseErrStatus statusCode, String errMessage) {
        super(errMessage);
        this.statusCode = statusCode;
    }

    final public ResponseErrStatus getStatusCode() {
        return statusCode;
    }
}
