package com.project.batch.repository

import com.project.batch.constants.Region
import com.project.batch.constants.Status
import com.project.batch.domain.TechBlog
import com.project.batch.utils.toInstantRange
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import tools.jackson.databind.ObjectMapper
import tools.jackson.module.kotlin.readValue
import java.time.Clock
import java.time.Instant
import java.time.LocalDate

@Repository
class TechBlogRepository(
    private val databaseClient: DatabaseClient,
    private val clock: Clock,
    private val objectMapper: ObjectMapper
) {

    suspend fun bulkInsert(blogs: List<TechBlog>) {
        if (blogs.isEmpty()) return

        val placeholders = blogs.joinToString(", ") { "(?, ?, ?, ?, ?, ?, ?, ?)" }
        val sql = """
            INSERT INTO tech_blog (id, source, region, title, url, tags, published_at, status)
            VALUES $placeholders
            ON DUPLICATE KEY UPDATE url = url
        """.trimIndent()

        var spec = databaseClient.sql(sql)
        blogs.forEachIndexed { i, blog ->
            val base = i * 8
            spec = spec
                .bind(base, blog.id)
                .bind(base + 1, blog.source)
                .bind(base + 2, blog.region.name)
                .bind(base + 3, blog.title)
                .bind(base + 4, blog.url)
            spec = if (blog.tags.isNotEmpty()) spec.bind(base + 5, objectMapper.writeValueAsString(blog.tags))
                   else spec.bindNull(base + 5, String::class.java)
            spec = spec
                .bind(base + 6, blog.publishedAt)
                .bind(base + 7, blog.status.code)
        }

        spec.fetch().rowsUpdated().awaitSingle()
    }

    suspend fun selectBlogsPublishedToday(date: LocalDate): List<TechBlog> {
        val (start, end) = date.toInstantRange(clock.zone)
        return databaseClient.sql(
            """
            SELECT * FROM tech_blog
            WHERE published_at >= :start AND published_at < :end
        """.trimIndent()
        )
            .bind("start", start)
            .bind("end", end)
            .map { row ->
                val tags = row["tags", String::class.java]
                TechBlog(
                    id = row["id", Long::class.java]!!,
                    source = row["source", String::class.java]!!,
                    region = Region.fromCode(row["region", String::class.java]!!),
                    title = row["title", String::class.java]!!,
                    url = row["url", String::class.java]!!,
                    publishedAt = row["published_at", Instant::class.java]!!,
                    tags = tags?.let { objectMapper.readValue<List<String>>(it) } ?: emptyList(),
                    status = Status.fromCode(row["status", String::class.java]!!),
                )
            }
            .all()
            .collectList()
            .awaitSingle()
    }
}
