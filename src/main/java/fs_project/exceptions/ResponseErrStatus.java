package fs_project.exceptions;

import org.springframework.http.HttpStatus;

public enum ResponseErrStatus {

    // GENERIC
    SOMETHING_WENT_WRONG(HttpStatus.I_AM_A_TEAPOT),

    // DB
    DB_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR),
    DB_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR),

    // MAPPING
    UNEXPECTED_MAPPING_FAIL(HttpStatus.INTERNAL_SERVER_ERROR),

    // RESERVATION
    FORBIDDEN_ROLE(HttpStatus.FORBIDDEN),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED),
    ILLEGAL_RESERVATION_TYPE(HttpStatus.BAD_REQUEST)


    ;

    private final HttpStatus statusCode;

    ResponseErrStatus(HttpStatus statusCode){
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
