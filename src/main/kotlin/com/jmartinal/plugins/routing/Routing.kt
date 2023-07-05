package com.jmartinal.plugins.routing

import com.jmartinal.plugins.routing.notes.addNoteRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        addNoteRoutes()
    }
}
