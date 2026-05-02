package com.project.batch.utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun LocalDate.toInstantRange(zone: ZoneId): Pair<Instant, Instant> {
    val start = atStartOfDay(zone).toInstant()
    val end = plusDays(1).atStartOfDay(zone).toInstant()
    return start to end
}
