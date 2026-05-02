package com.project.batch.remote.strategy.blog

import com.microsoft.playwright.Playwright
import com.microsoft.playwright.options.LoadState
import com.project.batch.constants.CollectionType
import com.project.batch.constants.Source
import com.project.batch.domain.TechBlog
import com.project.batch.remote.strategy.CrawlRegistry
import com.project.batch.remote.utils.HtmlBlogParser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class PlaywrightBlogCollectorStrategy(
    private val parser: HtmlBlogParser,
    @Qualifier(value = "playWriteBlogCoroutineDispatcher") private val dispatcher: CoroutineDispatcher,
) : BlogCollectorStrategy {

    override fun supports(type: CollectionType): Boolean = type == CollectionType.PLAYWRIGHT

    override suspend fun collect(source: Source): List<TechBlog> {
        val spec = CrawlRegistry[source] ?: return emptyList()
        val html = withContext(dispatcher) {
            Playwright.create().use { playwright ->
                playwright.chromium().launch().use { browser ->
                    browser.newPage().use { page ->
                        page.navigate(source.url)
                        page.waitForLoadState(LoadState.NETWORKIDLE)
                        page.content()
                    }
                }
            }
        }
        return parser.parse(html, source, spec)
    }
}