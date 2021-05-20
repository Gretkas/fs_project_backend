package fs_project.exceptions;

public class BadRequestException extends CustomException {

    public BadRequestException(ResponseErrStatus statusCode, String errMessage, Throwable err) {
        super(statusCode, errMessage, err);
    }

    public BadRequestException(ResponseErrStatus statusCode, String errMessage) {
        super(statusCode, errMessage);
    }
}
