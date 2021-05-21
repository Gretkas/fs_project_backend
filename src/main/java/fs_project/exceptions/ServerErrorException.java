package fs_project.exceptions;

public class ServerErrorException extends CustomException{
    public ServerErrorException(ResponseErrStatus statusCode, String errMessage, Throwable err) {
        super(statusCode, errMessage, err);
    }

    public ServerErrorException(ResponseErrStatus statusCode, String errMessage) {
        super(statusCode, errMessage);
    }
}
