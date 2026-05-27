package com.project.batch.remote.strategy

import com.project.batch.constants.Source

object CrawlRegistry {

    private val spec = mapOf(
        Source.NAVER_D2 to HtmlCrawlSpec(
            listSelector = ".post_list .post_item",
            titleSelector = ".title",
            linkSelector = "a",
            dateSelector = ".date",
            useDateText = true,
        ),
        Source.TOSS_TECH to HtmlCrawlSpec(
            listSelector = "article",
            titleSelector = "h2",
            linkSelector = "a",
            dateSelector = "time",
        ),
        Source.KAKAO_BANK to HtmlCrawlSpec(
            listSelector = "article",
            titleSelector = "h2",
            linkSelector = "a",
            dateSelector = "time",
        ),
        Source.OLIVE_YOUNG to HtmlCrawlSpec(
            listSelector = "article",
            titleSelector = "h2",
            linkSelector = "a",
            dateSelector = "time",
        ),
    )

    operator fun get(source: Source): HtmlCrawlSpec? = spec[source]
}