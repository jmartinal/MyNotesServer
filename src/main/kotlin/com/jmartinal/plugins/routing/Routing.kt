package com.jmartinal.plugins.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        addNoteRoutes()
    }
}
