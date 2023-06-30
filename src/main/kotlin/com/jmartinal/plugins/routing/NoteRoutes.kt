package com.jmartinal.plugins.routing

import com.jmartinal.plugins.routing.NoteRoutePaths.ROOT_PATH
import com.jmartinal.repositories.NotesRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

object NoteRoutePaths {
    const val ROOT_PATH = "notes"
}

fun Routing.addNoteRoutes() {
    route("/$ROOT_PATH") {
        get {
            call.respond(NotesRepository.getAll())
        }
    }
}