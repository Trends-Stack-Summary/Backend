package com.project.admin.exception.common;

import com.project.admin.exception.base.BaseErrorCode;
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
}
