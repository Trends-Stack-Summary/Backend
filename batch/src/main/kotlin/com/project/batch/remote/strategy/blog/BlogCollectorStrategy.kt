<<<<<<<< HEAD:batch/src/main/kotlin/com/project/batch/remote/strategy/blog/BlogCollectorStrategy.kt
package com.project.batch.remote.strategy.blog
========
package com.project.batch.remote.strategy
>>>>>>>> f924dd8 (#20 feat: implement API module for tech blog and release note):batch/src/main/kotlin/com/project/batch/remote/strategy/BlogCollectorStrategy.kt

import com.project.batch.constants.Source
import com.project.batch.constants.CollectionType
import com.project.batch.domain.TechBlog

interface BlogCollectorStrategy {

    fun supports(type: CollectionType): Boolean

    suspend fun collect(source: Source): List<TechBlog>
}