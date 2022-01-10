package com.example.newnoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


data class NoteModel( val id: String, val note: String)

class MainActivity : AppCompatActivity() {
    private lateinit var rvNotes: RecyclerView
    private lateinit var noteAdapter: itemAdapter
    private lateinit var note: EditText

    lateinit var mainViewModel: MainViewModel
    lateinit var AllNotes: ArrayList<NoteModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AllNotes = arrayListOf()

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getNotes().observe(this, {
                notes -> noteAdapter.update(notes)
        })

        note = findViewById(R.id.noteText)


        rvNotes = findViewById(R.id.recy)
        noteAdapter = itemAdapter(this)
        rvNotes.adapter = noteAdapter
        rvNotes.layoutManager = LinearLayoutManager(this)

        mainViewModel.getData()
    }

    fun addNote(view: View) {
        mainViewModel.addNote(NoteModel("", note.text.toString()))
        note.text.clear()
        Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
    }


    fun raiseDialog(id: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Update Note")
        val updatedNote = EditText(this)
        updatedNote.hint = "Enter new text"

        builder.setPositiveButton("Save") { dialog, which ->
            mainViewModel.editNote(id, updatedNote.text.toString())
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        val alert = builder.create()
        alert.setView(updatedNote)
        alert.show()
    }
}