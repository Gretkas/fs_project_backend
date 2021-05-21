package fs_project.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Enum for HTTP statuses.
 */
public enum ResponseErrStatus {

    /**
     * Something went wrong response err status.
     */
// GENERIC
    SOMETHING_WENT_WRONG(HttpStatus.I_AM_A_TEAPOT),

    /**
     * Db save failed response err status.
     */
// DB
    DB_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * Db update failed response err status.
     */
    DB_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR),

    /**
     * Unexpected mapping fail response err status.
     */
// MAPPING
    UNEXPECTED_MAPPING_FAIL(HttpStatus.INTERNAL_SERVER_ERROR),

    /**
     * Forbidden role response err status.
     */
// RESERVATION
    FORBIDDEN_ROLE(HttpStatus.FORBIDDEN),
    /**
     * User not found response err status.
     */
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED),
    /**
     * Illegal reservation type response err status.
     */
    ILLEGAL_RESERVATION_TYPE(HttpStatus.BAD_REQUEST)


    ;

    private final HttpStatus statusCode;

    ResponseErrStatus(HttpStatus statusCode){
        this.statusCode = statusCode;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
