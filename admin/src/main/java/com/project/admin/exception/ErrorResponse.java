package com.project.admin.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private  final  String code;
    private  final  String message;


    private ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus().value();
        this.code= errorCode.name();
        this.message = errorCode.getMessage();
    }


    public  static  ErrorResponse from(ErrorCode errorCode) {

        return new ErrorResponse(errorCode);
    }
}
