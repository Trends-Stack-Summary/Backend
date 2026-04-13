package com.project.admin.exception.base;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    HttpStatus status();

    String message();
}
