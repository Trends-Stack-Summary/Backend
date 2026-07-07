package com.project.crawler.exception;

import lombok.Getter;

@Getter
public class CrawlerException extends BaseException {

    public CrawlerException(CrawlerErrorCode errorCode) {
        super(errorCode);
    }
}
