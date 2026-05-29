package com.project.api.service.dto

import com.project.api.constants.Category
import com.project.api.constants.TechStack
import com.project.api.entity.GithubRelease
import java.time.LocalDateTime

data class ReleaseNoteDetailResults(
    val id: Long,
    val techStack: TechStack,
    val tagName: String,
    val name: String?,
    val body: String?,
    val publishedAt: LocalDateTime,
    val prerelease: Boolean,
    val category: Category,
) {
    companion object {
        fun from(release: GithubRelease) = ReleaseNoteDetailResults(
            id = release.id,
            techStack = release.techStack,
            tagName = release.tagName,
            name = release.name,
            body = release.body,
            publishedAt = release.publishedAt,
            prerelease = release.prerelease,
            category = Category.from(release.techStack)
        )
    }

}

