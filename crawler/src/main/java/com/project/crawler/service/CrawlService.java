package com.project.crawler.service;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class CrawlService {

    private final RestClient crawlingRestClient;

    private static final String USER_AGENT =
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";

    public CrawlService(@Qualifier("crawlingRestClient") RestClient crawlingRestClient) {
        this.crawlingRestClient = crawlingRestClient;
    }

    @Retryable(
            includes = {HttpServerErrorException.class,
                    HttpClientErrorException.TooManyRequests.class},
            maxRetries = 2,
            delay = 1000,
            multiplier = 2.0,
            timeUnit = TimeUnit.MILLISECONDS

    )
    public String crawl(String url) {

        String html = crawlingRestClient.get()
                .uri(url)
                .header("User-Agent", USER_AGENT)
                .header("Referer", "https://www.google.com")
                .retrieve()
                .body(String.class);

        Document doc = Jsoup.parse(html);
        return doc.body().text();
    }
}
