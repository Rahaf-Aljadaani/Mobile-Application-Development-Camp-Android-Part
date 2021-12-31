package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

data class NoteModel(val id: Int, val note: String)

class MainActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHandler
    private lateinit var note: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHandler(this)

        note = findViewById(R.id.noteText)

    }

    fun addNote(view: View) {
        db.addNote(NoteModel(0, note.text.toString()))
        note.text.clear()
        Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
    }
}