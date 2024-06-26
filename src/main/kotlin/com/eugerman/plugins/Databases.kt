package com.eugerman.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    Database.connect(
        url = "jdbc:mysql://localhost:8306/tutorial_db",
        user = "german",
        password = "p@ss"
    )
}
