package com.project.batch.domain

import com.project.batch.constants.Region
import com.project.batch.constants.Status
import lombok.Getter
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("tech_blog")
@Getter
data class TechBlog(
    @Id val id: Long,
    val source: String,
    val region: Region,
    val title: String,
    val url: String,
    val publishedAt: Instant,
    val tags: List<String>,
    val status: Status = Status.PENDING,
)