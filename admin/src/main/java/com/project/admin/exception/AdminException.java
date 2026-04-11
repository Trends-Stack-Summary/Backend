package com.project.admin.exception;

import lombok.Getter;

@Getter
public class AdminException extends RuntimeException {

    private  final ErrorCode errorCode;

    public AdminException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;

    }
}
