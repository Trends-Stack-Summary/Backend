package com.project.api.entity.company

import com.project.api.constants.Source
import java.time.LocalDateTime

data class CompanyMappingResult(
    val source: Source,
    val totalCount: Long,
    val lastPostedAt: LocalDateTime?,
)