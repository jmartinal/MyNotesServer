package com.jmartinal.repositories

import com.jmartinal.mappers.toNote
import com.jmartinal.models.Note
import com.jmartinal.mynotes.database.MyNotesDatabase
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import java.io.File

private const val DATABASE_FILE_NAME = "MyNotesDatabase.db"

object NotesRepository {

    private val notesDB = JdbcSqliteDriver(url = "jdbc:sqlite:$DATABASE_FILE_NAME").let {
        if (!File(DATABASE_FILE_NAME).exists()) MyNotesDatabase.Schema.create(it)
        MyNotesDatabase(it)
    }

    // CREATE
    fun save(note: Note): Note {
        notesDB.noteQueries.insert(note.title, note.description, note.type.name)
        return notesDB.noteQueries.selectLastInsertedNote().executeAsOne().toNote()
    }

    // READ
    fun getAll(): List<Note> = notesDB.noteQueries.select().executeAsList().map { it.toNote() }

    fun getById(id: Long): Note? = notesDB.noteQueries.selectById(id).executeAsOneOrNull()?.toNote()

    // UPDATE
    fun update(note: Note): Boolean {
        if (getById(note.id) == null) return false
        notesDB.noteQueries.update(note.title, note.description, note.type.name, note.id)
        return true
    }

    // DELETE
    fun delete(id: Long): Boolean {
        if (getById(id) == null) return false
        notesDB.noteQueries.delete(id)
        return true
    }
}