package com.project.batch.constants

enum class Region(val code: String, val ko: String) {
    DOMESTIC("DOMESTIC", "국내"),
    INTERNATIONAL("INTERNATIONAL", "국외");

    companion object {
        fun fromCode(code: String): Region =
            entries.firstOrNull { it.code == code }
                ?: throw IllegalArgumentException("Invalid region code: $code")
    }
}