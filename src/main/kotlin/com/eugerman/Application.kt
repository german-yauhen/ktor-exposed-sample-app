package com.eugerman

import com.eugerman.model.PostgresTaskRepository
import com.eugerman.plugins.configureDatabases
import com.eugerman.plugins.configureRouting
import com.eugerman.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val repository = PostgresTaskRepository()
    configureSerialization(repository)
    configureDatabases()
    configureRouting()
}
