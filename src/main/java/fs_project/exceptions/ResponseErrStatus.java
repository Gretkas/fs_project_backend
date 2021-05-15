package fs_project.exceptions;

import org.springframework.http.HttpStatus;

public enum ResponseErrStatus {

    //RESERVATION
    FORBIDDEN_ROLE(HttpStatus.FORBIDDEN),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED)


    ;

    private final HttpStatus statusCode;

    ResponseErrStatus(HttpStatus statusCode){
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
