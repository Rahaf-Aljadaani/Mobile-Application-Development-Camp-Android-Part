package com.example.noteapp


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: NoteModel)

    @Query("SELECT * FROM NotesTable ORDER BY id ASC")
    fun getNotes(): LiveData<List<NoteModel>>

    @Update
    suspend fun updateNote(note: NoteModel)

    @Delete
    suspend fun deleteNote(note: NoteModel)

}