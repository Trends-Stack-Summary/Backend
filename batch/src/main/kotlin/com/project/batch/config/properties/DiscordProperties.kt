package com.project.batch.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "discord")
data class DiscordProperties(val channel: Map<String, String> = emptyMap())