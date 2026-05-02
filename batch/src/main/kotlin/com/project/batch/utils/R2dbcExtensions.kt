package com.project.batch.utils

import io.r2dbc.spi.Statement
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun Statement.executeAndCount(): Mono<Void> =
    Flux.from(execute()).flatMap { Mono.from(it.rowsUpdated) }.then()
