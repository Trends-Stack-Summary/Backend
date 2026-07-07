package com.project.crawler.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CrawlerErrorCode implements  BaseErrorCode{

    BLOG_NOTFOUND(HttpStatus.NOT_FOUND, "해당 글을 찾을 수 없습니다"),
    BLOG_FORBIDDEN(HttpStatus.FORBIDDEN, "블로그 서버로부터 차단당했습니다."),
    CRAWL_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "네트워크 타임아웃이 발생 했습니다");

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus status() {
        return status;
    }

    @Override
    public String message() {
        return message;
    }


}
