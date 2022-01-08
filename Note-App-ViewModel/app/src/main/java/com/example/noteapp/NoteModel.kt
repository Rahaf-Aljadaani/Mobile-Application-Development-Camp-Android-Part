package com.example.noteapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "NotesTable")
data class NoteModel(@PrimaryKey(autoGenerate = true) val id: Int, val note: String)
