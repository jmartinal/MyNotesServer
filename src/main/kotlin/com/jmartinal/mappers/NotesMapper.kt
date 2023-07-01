package com.jmartinal.mappers

import com.jmartinal.models.Note
import com.jmartinal.mynotes.database.DbNote

fun DbNote.toNote() = Note(
    id = id,
    title = title,
    description = description,
    type = Note.Type.valueOf(type)
)