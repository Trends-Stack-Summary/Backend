package com.project.admin.exception.base;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public BaseException(BaseErrorCode errorCode) {

        super(errorCode.message());
        this.errorCode = errorCode;
    }


}
