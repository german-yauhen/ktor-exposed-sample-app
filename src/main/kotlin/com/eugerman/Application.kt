package com.eugerman

import com.eugerman.model.DummyTaskRepository
import com.eugerman.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val repository = DummyTaskRepository()
    configureSerialization(repository)
    configureDatabases()
    configureRouting()
}
