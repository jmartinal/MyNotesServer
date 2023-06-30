package com.jmartinal.repositories

import com.jmartinal.models.Note

object NotesRepository {

    private val notes = mutableListOf<Note>()
    private var currentId: Long = 1L

    // CREATE
    fun save(note: Note): Note = note
        .copy(id = currentId++)
        .also(notes::add)

    // READ
    fun getAll(): List<Note> = notes

    fun getById(id: Long): Note? = notes.find { it.id == id }

    // UPDATE
    fun update(note: Note): Boolean = notes
        .indexOfFirst { it.id == note.id }
        .takeIf { it >= 0 }
        ?.also { notes[it] = note }
        .let { it != null }

    // DELETE
    fun delete(id: Long): Boolean = notes
        .indexOfFirst { it.id == id }
        .takeIf { it >= 0 }
        ?.also(notes::removeAt)
        .let { it != null }
}