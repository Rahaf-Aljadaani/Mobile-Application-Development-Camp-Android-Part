package com.example.notesapproom

class Repository(private val noteDao: NoteDao) {

    val getNotes: List<NoteMoudel> = noteDao.getNotes()

    suspend fun addNote(note: NoteMoudel){
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: NoteMoudel){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: NoteMoudel){
        noteDao.deleteNote(note)
    }

}