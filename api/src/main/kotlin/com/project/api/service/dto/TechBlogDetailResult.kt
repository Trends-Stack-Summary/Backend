package com.project.api.service.dto

import com.project.api.constants.Region
import com.project.api.constants.Source
import com.project.api.entity.TechBlog
import java.time.LocalDateTime

data class TechBlogDetailResult(
    val id: Long,
    val source: Source,
    val region: Region,
    val title: String,
    val url: String,
    val tags: List<String>,
    val publishedAt: LocalDateTime,
    val summary: String?,
) {
    companion object {
        fun from(techBlog: TechBlog) = TechBlogDetailResult (
            id =techBlog.id,
            source = techBlog.source,
            region = techBlog.region,
            title = techBlog.title,
            url = techBlog.url,
            publishedAt = techBlog.publishedAt,
            tags = techBlog.tags ?: emptyList(),
            summary =  techBlog.approval?.summary
        )

    }
}
