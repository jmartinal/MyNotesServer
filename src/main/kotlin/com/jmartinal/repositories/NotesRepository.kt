package com.jmartinal.repositories

import com.jmartinal.models.Note

object NotesRepository {

    private val notes = mutableListOf<Note>()
    private var currentId: Long = 1L

    // CREATE
    fun save(note: Note): Note {
        val noteToSave = note.copy(id = currentId++)
        notes.add(noteToSave)
        return noteToSave
    }

    // READ
    fun getAll(): List<Note> = notes

    fun getById(id: Long): Note? = notes.find { it.id == id }

    // UPDATE
    fun update(note: Note): Boolean {
        val index = notes.indexOfFirst { it.id == note.id }
        return when(index >= 0) {
            true -> {
                notes[index] = note
                true
            }
            false -> false
        }
    }

    // DELETE
    fun delete(id: Long): Boolean {
        val index = notes.indexOfFirst { it.id == id }
        return when(index >= 0) {
            true -> {
                notes.removeAt(index)
                true
            }
            false -> false
        }
    }
}