package com.jmartinal.repositories

import com.jmartinal.models.Note
import com.jmartinal.models.Note.Type.AUDIO
import com.jmartinal.models.Note.Type.TEXT

object NotesRepository {

    fun getAll(size: Int = 10): List<Note> = (1..size).map { position ->
        Note(
            title = "Title $position",
            description = "Description $position",
            type = if (position % 3 == 0) AUDIO else TEXT
        )
    }
}