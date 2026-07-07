package com.project.crawler.exception;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    HttpStatus status();

    String message();
}