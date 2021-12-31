package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class NoteModel(val id: Int, val note: String)

class MainActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHandler
    private lateinit var rvNotes: RecyclerView
    private lateinit var note: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHandler(this)

        note = findViewById(R.id.noteText)
        rvNotes = findViewById(R.id.recycler)
        updateRV()
    }

    fun addNote(view: View) {
        db.addNote(NoteModel(0, note.text.toString()))
        note.text.clear()
        updateRV()
        Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
    }

    private fun updateRV(){
        rvNotes.adapter = itemAdapter( db.viewNotes())
        rvNotes.layoutManager = LinearLayoutManager(this)
    }

    private fun getItemsList(): ArrayList<NoteModel>{
        return db.viewNotes()
    }
}