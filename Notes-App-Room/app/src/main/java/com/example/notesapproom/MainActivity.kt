package com.example.notesapproom

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    private val noteDao by lazy { NoteDatabase.getDatabase(this).noteDao() }
    private val repository by lazy { Repository(noteDao) }

    lateinit var rvNotes: RecyclerView
    lateinit var note: EditText

    private lateinit var AllNotes: List<NoteMoudel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AllNotes = listOf()

        note = findViewById(R.id.ed)

        getItemsList()

        rvNotes = findViewById(R.id.rvMain)
        updateRV()
    }


    private fun updateRV(){
        rvNotes.adapter = itemAdapter(this, AllNotes)
        rvNotes.layoutManager = LinearLayoutManager(this)
    }

    private fun getItemsList(){
        CoroutineScope(IO).launch {
            val data = async {
                repository.getNotes
            }.await()
            if(data.isNotEmpty()){
                AllNotes = data
                updateRV()
            }else{
                Log.e("MainActivity", "Unable to get data", )
            }
        }
    }


    fun addNote(view: View) {
        CoroutineScope(IO).launch {
            repository.addNote(NoteMoudel(0, note.text.toString()))
        }
        updateRV()
        note.text.clear()
        Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
    }

    private fun editNote(noteID: Int, noteText: String){
        CoroutineScope(IO).launch {
            repository.updateNote(NoteMoudel(noteID,noteText))
            updateRV()
        }
    }

    fun deleteNote(noteID: Int){
        CoroutineScope(IO).launch {
            repository.deleteNote(NoteMoudel(noteID,""))
            updateRV()
        }
    }



    fun raiseDialog(id: Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Update Note")
        val updatedNote = EditText(this)
        updatedNote.hint = "Enter new text"

        builder.setPositiveButton("Save") { dialog, which ->
           editNote(id, updatedNote.text.toString())
            updateRV()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        val alert = builder.create()
        alert.setView(updatedNote)
        alert.show()
    }
}