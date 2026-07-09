package com.project.batch.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMqConfig {
    @Bean
    fun crawlQueue(): Queue {
        return Queue("crawlerQueue", true)
    }

    @Bean
    fun crawlExchange(): DirectExchange {
        return DirectExchange("crawlerExchange")
    }

    @Bean
    fun crawlBinding(crawlQueue: Queue, crawlExchange: DirectExchange): Binding {
        return BindingBuilder.bind(crawlQueue)
            .to(crawlExchange)
            .with("crawlRouting")
    }
}
