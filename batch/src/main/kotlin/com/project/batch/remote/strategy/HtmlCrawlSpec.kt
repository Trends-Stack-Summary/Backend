package com.project.batch.remote.strategy

data class HtmlCrawlSpec(
    val listSelector: String,
    val titleSelector: String,
    val linkSelector: String,
    val dateSelector: String,
    val dateAttribute: String = "datetime",
    val useDateText: Boolean = false,
    val tagSelector: String? = null,
)