package com.example.notesapproom

import androidx.room.*


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: NoteMoudel)

    @Query("SELECT * FROM NotesTable ORDER BY id ASC")
    fun getNotes(): List<NoteMoudel>

    @Update
    suspend fun updateNote(note: NoteMoudel)

    @Delete
    suspend fun deleteNote(note: NoteMoudel)

}