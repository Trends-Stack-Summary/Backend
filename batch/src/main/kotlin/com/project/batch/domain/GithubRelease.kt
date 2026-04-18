package com.project.batch.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.UUID

@Table("github_release")
data class GithubRelease(
    @Id val id: String = UUID.randomUUID().toString(),
    val techStack: String,
    val tagName: String,
    val name: String?,
    val body: String?,
    val publishedAt: Instant,
    val prerelease: Boolean,
    val draft: Boolean,
)