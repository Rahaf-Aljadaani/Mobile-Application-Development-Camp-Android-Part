package com.example.noteapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO


class MainActivity : AppCompatActivity() {
    private lateinit var Adapter: itemAdapter
    lateinit var rvNotes: RecyclerView

    lateinit var note: EditText
    lateinit var AllNotes: List<NoteModel>

    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AllNotes = listOf()
        note = findViewById(R.id.noteText)
        rvNotes = findViewById(R.id.recycler)



        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getNotes().observe(this, {
               notes -> Adapter.update(notes)
        })

        Adapter = itemAdapter(this)
        rvNotes.adapter = Adapter
        rvNotes.layoutManager = LinearLayoutManager(this)
    }



    fun addNote(view: View) {
        mainViewModel.addNote(note.text.toString())
        note.text.clear()
        Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
    }

    fun raiseDialog(id: Int){
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