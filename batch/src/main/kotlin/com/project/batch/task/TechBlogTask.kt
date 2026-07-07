package com.project.batch.task

import com.project.batch.constants.Source
import com.project.batch.remote.collector.TechBlogCollector
import com.project.batch.repository.TechBlogRepository
import com.project.batch.service.NotificationService
import kotlinx.coroutines.supervisorScope
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.reactive.TransactionalOperator
import org.springframework.transaction.reactive.executeAndAwait
import java.time.Clock
import java.time.LocalDate

@Component
class TechBlogTask(
    private val techBlogCollector: TechBlogCollector,
    private val techBlogRepository: TechBlogRepository,
    private val notificationService: NotificationService,
    private val transactionalOperator: TransactionalOperator,
    private val rabbitTemplate: RabbitTemplate,
    private val clock: Clock,
) {

    companion object {
        private val log = LoggerFactory.getLogger(TechBlogTask::class.java)
    }

    @Scheduled(cron = "\${batch.cron.tech-blog}", zone = "Asia/Seoul")
    suspend fun collectTechBlogs() = supervisorScope {
        log.info("기술 블로그 수집 시작")
        val blogs = techBlogCollector.collectAll(Source.entries)
        transactionalOperator.executeAndAwait { techBlogRepository.bulkInsert(blogs) }

        log.info("기술 블로그 저장 완료. RabbitMQ 시작. size: ${blogs.size}")
        blogs.forEach{blog ->
            rabbitTemplate.convertAndSend(
                "crawlerExchange",
                "crawlRouting",
                blog.url
            )
        }
        notifyTodayBlogs()
        log.info("기술 블로그 수집 완료")
    }

    private suspend fun notifyTodayBlogs() {
        val today = LocalDate.now(clock)
        val todayBlogs = techBlogRepository.selectBlogsPublishedToday(today)
        if (todayBlogs.isNotEmpty()) {
            notificationService.sendTechBlogNotification(todayBlogs, today)
        }
    }
}