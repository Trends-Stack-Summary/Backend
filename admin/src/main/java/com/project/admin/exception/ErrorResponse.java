package com.project.admin.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String message;


    private ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }


    public static ErrorResponse from(BaseErrorCode errorCode) {

        return new ErrorResponse(errorCode.status().value(), errorCode.message());
    }

    public static  ErrorResponse of (int status , String message) {
        return new ErrorResponse(status, message);
    }
}
