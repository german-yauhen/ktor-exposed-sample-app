package com.eugerman.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    Database.connect(
        url = "jdbc:postgresql://localhost:8432/ktor_tutorial_db",
        user = "eugerman",
        password = "p@ss"
    )
}
