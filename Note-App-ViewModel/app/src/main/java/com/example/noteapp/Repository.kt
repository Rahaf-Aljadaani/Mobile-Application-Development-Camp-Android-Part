package com.example.noteapp
import androidx.lifecycle.LiveData


class Repository(private val noteDao: NoteDao) {


    val getNotes: LiveData<List<NoteModel>> = noteDao.getNotes()

    suspend fun addNote(note: NoteModel){
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: NoteModel){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: NoteModel){
        noteDao.deleteNote(note)
    }

}