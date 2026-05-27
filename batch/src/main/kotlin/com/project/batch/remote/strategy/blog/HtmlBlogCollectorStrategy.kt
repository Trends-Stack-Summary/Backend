package com.project.batch.remote.strategy.blog

import com.project.batch.constants.CollectionType
import com.project.batch.constants.Source
import com.project.batch.domain.TechBlog
import com.project.batch.remote.strategy.CrawlRegistry
import com.project.batch.remote.utils.HtmlBlogParser
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class HtmlBlogCollectorStrategy(
    @Qualifier("techBlogWebClient") private val client: WebClient,
    private val parser: HtmlBlogParser,
) : BlogCollectorStrategy {

    override fun supports(type: CollectionType): Boolean = type == CollectionType.HTML

    override suspend fun collect(source: Source): List<TechBlog> {
        val spec = CrawlRegistry[source] ?: return emptyList()
        val html = client.get()
            .uri(source.url)
            .retrieve()
            .bodyToMono<String>()
            .awaitSingleOrNull() ?: return emptyList()
        return parser.parse(html, source, spec)
    }
}