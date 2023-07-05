package com.jmartinal.plugins.routing.notes

object ErrorMessages {
    const val MALFORMED_JSON_OBJECT = "Malformed JSON Data object: %s"
    const val MISSING_OR_MALFORMED_ID = "Missing or malformed id."
    const val NOTE_NOT_FOUND = "Note with id %s not found."
}

object ParamNames {
    const val ID = "id"
}

object Params {
    const val ID = "{${ParamNames.ID}}"
}

object Paths {
    const val ROOT = "notes"
}
