package com.project.api.controller.dto

import com.project.api.constants.Region
import com.project.api.constants.Source
import com.project.api.service.dto.TechBlogDetailResult
import java.time.LocalDateTime

data class TechBlogDetailResponse(
    val id: String,
    val source: Source,
    val region: Region,
    val title: String,
    val url: String,
    val publishedAt: LocalDateTime,
    val tags: List<String>,
    val summary: String?,
) {
    companion object {
        fun of(result: TechBlogDetailResult) = TechBlogDetailResponse(
            id = result.id.toString(),
            source = result.source,
            region = result.region,
            title = result.title,
            url = result.url,
            publishedAt = result.publishedAt,
            tags = result.tags,
            summary = result.summary,
        )
    }
}

