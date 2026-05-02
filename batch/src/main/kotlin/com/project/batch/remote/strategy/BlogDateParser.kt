package com.project.batch.remote.strategy

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object BlogDateParser {

    private val ZONE_SEOUL = ZoneId.of("Asia/Seoul")
    private val DOT_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    private val DASH_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun parse(dateStr: String): Instant? {
        if (dateStr.isBlank()) return null
        return runCatching { Instant.parse(dateStr) }
            .recoverCatching { ZonedDateTime.parse(dateStr).toInstant() }
            .recoverCatching { LocalDate.parse(dateStr.trim(), DOT_FORMAT).atStartOfDay(ZONE_SEOUL).toInstant() }
            .recoverCatching { LocalDate.parse(dateStr.trim(), DASH_FORMAT).atStartOfDay(ZONE_SEOUL).toInstant() }
            .getOrNull()
    }
}