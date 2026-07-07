package com.project.crawler.service;

import com.project.crawler.entity.TechBlog;
import com.project.crawler.exception.BaseException;
import com.project.crawler.exception.CrawlerErrorCode;
import com.project.crawler.repositroy.TechBlogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrawlStorageService {

    private final CrawlService crawlService;

    private final CrawlerContentService crawlerContentService;
    private final TechBlogRepository techBlogRepository;


    public void crawl(String url) {
        TechBlog techBlog = techBlogRepository.findByUrl(url).orElseThrow(() -> new IllegalStateException("해당 글이 존재하지 않습니다"));
        try {
            String content = crawlService.crawl(url);
            crawlerContentService.saveSuccess(techBlog, content);
        } catch (HttpClientErrorException.Forbidden e) {
            log.warn("403 url={}", url);
            crawlerContentService.handle403(techBlog);
        } catch (Exception e) {
            log.warn("크롤링 실패 url ={}", url);
            log.info("오류 ={}", e.getMessage());
            crawlerContentService.handleTimeout(techBlog, e.getClass().getSimpleName());
        }
    }
}
