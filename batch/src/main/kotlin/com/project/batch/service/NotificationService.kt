package com.project.batch.service

import com.project.batch.constants.Source
import com.project.batch.domain.GithubRelease
import com.project.batch.domain.TechBlog
import com.project.batch.service.dto.DiscordEmbed
import com.project.batch.service.dto.DiscordEmbedField
import com.project.batch.service.dto.DiscordWebhookRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodilessEntity
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
class NotificationService(
    @Qualifier("githubReleaseDiscordWebhookUrl") private val githubReleaseWebhookUrl: String,
    @Qualifier("techblogDiscordWebhookUrl") private val techblogWebhookUrl: String,
    @Qualifier("commonWebClient") private val client: WebClient,
) {

    companion object {
        private val log = LoggerFactory.getLogger(NotificationService::class.java)
        private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.of("Asia/Seoul"))
    }

    suspend fun sendTechBlogNotification(blogs: List<TechBlog>, date: LocalDate) {
        try {
            val today = date.format(dateFormatter)
            val fields = blogs.map { blog ->
                val displayName = runCatching { Source.valueOf(blog.source).displayName }.getOrElse { blog.source }
                DiscordEmbedField(
                    name = displayName,
                    value = "[${blog.title}](${blog.url}) · ${dateFormatter.format(blog.publishedAt.atZone(ZoneId.of("Asia/Seoul")))}",
                )
            }
            val embed = DiscordEmbed(title = "📝 $today 기술 블로그 ${blogs.size}건", fields = fields)
            postToDiscord(techblogWebhookUrl, embed)
        } catch (e: Exception) {
            log.error("Failed to send tech blog notification", e)
        }
    }

    suspend fun sendReleaseNotification(releases: List<GithubRelease>, date: LocalDate) {
        try {
            val today = date.format(dateFormatter)
            val fields = releases.map { release ->
                DiscordEmbedField(
                    name = release.techStack.displayName,
                    value = "[${release.tagName}](${release.techStack.releaseUrl(release.tagName)}) · ${dateTimeFormatter.format(release.publishedAt)}",
                )
            }
            val embed = DiscordEmbed(title = "🎉 $today 총 ${releases.size}건", fields = fields)
            postToDiscord(githubReleaseWebhookUrl, embed)
        } catch (e: Exception) {
            log.error("Failed to send release notification", e)
        }
    }

    private suspend fun postToDiscord(webhookUrl: String, embed: DiscordEmbed) {
        client.post()
            .uri(webhookUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(DiscordWebhookRequest(embeds = listOf(embed)))
            .retrieve()
            .awaitBodilessEntity()
    }
}
