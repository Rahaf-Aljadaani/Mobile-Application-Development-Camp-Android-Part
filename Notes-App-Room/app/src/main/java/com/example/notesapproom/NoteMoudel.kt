package com.example.notesapproom

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "NotesTable")
data class NoteMoudel(@PrimaryKey(autoGenerate = true) val id :Int, val note : String)