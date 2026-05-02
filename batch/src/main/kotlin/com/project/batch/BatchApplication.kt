package com.project.batch

import com.project.batch.config.R2dbcProperty
import com.project.batch.config.properties.DiscordProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableConfigurationProperties(
    value = [
        R2dbcProperty::class,
        DiscordProperties::class
    ]
)
@SpringBootApplication
class BatchApplication

fun main(args: Array<String>) {
    runApplication<BatchApplication>(*args)
}