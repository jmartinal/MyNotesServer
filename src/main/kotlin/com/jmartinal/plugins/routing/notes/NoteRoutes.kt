package com.jmartinal.plugins.routing.notes

import com.jmartinal.models.Note
import com.jmartinal.plugins.routing.notes.ErrorMessages.MALFORMED_JSON_OBJECT
import com.jmartinal.plugins.routing.notes.ErrorMessages.MISSING_OR_MALFORMED_ID
import com.jmartinal.plugins.routing.notes.ErrorMessages.NOTE_NOT_FOUND
import com.jmartinal.plugins.routing.notes.Paths.ROOT
import com.jmartinal.repositories.NotesRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.addNoteRoutes() {
    route("/$ROOT") {
        // CREATE
        post {
            try {
                val note = call.receive<Note>()
                val savedNote = NotesRepository.save(note)
                call.respond(HttpStatusCode.Created, savedNote)
            } catch (e: Exception) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = String.format(MALFORMED_JSON_OBJECT, e.message)
                )
            }
        }

        // READ
        get {
            call.respond(NotesRepository.getAll())
        }

        get("/${Params.ID}") {
            val id = call.parameters[ParamNames.ID]
                ?: return@get call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = MISSING_OR_MALFORMED_ID
                )

            val note = NotesRepository.getById(id.toLong())
                ?: return@get call.respond(
                    status = HttpStatusCode.NotFound,
                    message = String.format(NOTE_NOT_FOUND, id)
                )

            call.respond(note)
        }

        //UPDATE
        put {
            try {
                val note = call.receive<Note>()
                val success = NotesRepository.update(note)
                if (success) {
                    call.respond(note)
                } else {
                    call.respond(
                        status = HttpStatusCode.NotFound,
                        message = String.format(NOTE_NOT_FOUND, note.id)
                    )
                }
            } catch (e: java.lang.Exception) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = String.format(MALFORMED_JSON_OBJECT, e.message)
                )
            }
        }

        // DELETE
        delete(Params.ID) {
            val id = call.parameters[ParamNames.ID]
                ?: return@delete call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = MISSING_OR_MALFORMED_ID
                )

            val success = NotesRepository.delete(id.toLong())
            if (success) {
                call.respond(HttpStatusCode.Accepted)
            } else {
                call.respond(
                    status = HttpStatusCode.NotFound,
                    message = String.format(NOTE_NOT_FOUND, id)
                )
            }
        }
    }
}