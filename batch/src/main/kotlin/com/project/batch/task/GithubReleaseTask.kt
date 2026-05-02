package com.project.batch.task

import com.project.batch.constants.TechStack
import com.project.batch.remote.collector.GithubReleaseCollector
import com.project.batch.repository.GithubReleaseRepository
import com.project.batch.service.NotificationService
import kotlinx.coroutines.supervisorScope
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.reactive.TransactionalOperator
import org.springframework.transaction.reactive.executeAndAwait
import java.time.Clock
import java.time.LocalDate

@Component
class GithubReleaseTask(
    private val githubReleaseCollector: GithubReleaseCollector,
    private val githubReleaseRepository: GithubReleaseRepository,
    private val notificationService: NotificationService,
    private val transactionalOperator: TransactionalOperator,
    private val clock: Clock,
) {

    companion object {
        private val log = LoggerFactory.getLogger(GithubReleaseTask::class.java)
    }

    @Scheduled(cron = "\${batch.cron.github-release}", zone = "Asia/Seoul")
    suspend fun collectGithubReleases() = supervisorScope {
        log.info("Github 릴리즈 수집 시작")
        val releases = githubReleaseCollector.collectAll(TechStack.entries)
        transactionalOperator.executeAndAwait { githubReleaseRepository.bulkInsert(releases) }

        notifyTodayReleases()
        log.info("Github 릴리즈 수집 완료")
    }

    private suspend fun notifyTodayReleases() {
        val today = LocalDate.now(clock)
        val todayReleases = githubReleaseRepository.selectReleaseTodayPublished(today)
        if (todayReleases.isNotEmpty()) {
            notificationService.sendReleaseNotification(todayReleases, today)
        }
    }
}