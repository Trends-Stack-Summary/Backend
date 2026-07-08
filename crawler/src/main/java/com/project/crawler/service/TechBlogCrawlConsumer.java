package com.project.crawler.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TechBlogCrawlConsumer {

    private final CrawlStorageService crawlStorageService;

    @RabbitListener(queues = "crawlerQueue")
    public void consumerCrawl(String url) {
        log.info("RabbitMQ 시작, url={}", url);

        crawlStorageService.crawl(url);
        log.info("크롤링 성공  url={}", url);

    }

}
