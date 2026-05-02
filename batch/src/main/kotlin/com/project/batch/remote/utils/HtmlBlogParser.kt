package com.project.batch.remote.utils

import com.project.batch.constants.Source
import com.project.batch.domain.TechBlog
import com.project.batch.remote.strategy.BlogDateParser
import com.project.batch.remote.strategy.HtmlCrawlSpec
import com.project.batch.utils.Snowflake
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.stereotype.Component

@Component
class HtmlBlogParser(
    private val snowflake: Snowflake,
) {

    fun parse(html: String, source: Source, spec: HtmlCrawlSpec): List<TechBlog> =
        Jsoup.parse(html, source.url)
            .select(spec.listSelector)
            .mapNotNull { parseElements(it, source, spec) }

    private fun parseElements(element: Element, source: Source, spec: HtmlCrawlSpec): TechBlog? {
        val title = element.select(spec.titleSelector).text().takeIf { it.isNotBlank() } ?: return null
        val url = element.select(spec.linkSelector).attr("abs:href").takeIf { it.isNotBlank() } ?: return null
        val date = if (spec.useDateText) element.select(spec.dateSelector).text() else element.select(spec.dateSelector).attr(spec.dateAttribute)
        val publishedAt = BlogDateParser.parse(date) ?: return null
        val tags = spec.tagSelector?.let { selector -> element.select(selector).map { it.text() }.filter { it.isNotBlank() } } ?: emptyList()
        return TechBlog(
            id = snowflake.nextId(),
            source = source.name,
            region = source.region,
            title = title,
            url = url,
            publishedAt = publishedAt,
            tags = tags,
        )
    }
}