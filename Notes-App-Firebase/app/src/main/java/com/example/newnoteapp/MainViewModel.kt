package com.example.newnoteapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val notes: MutableLiveData<List<NoteModel>> = MutableLiveData()
    private var db: FirebaseFirestore = Firebase.firestore

    fun getNotes(): LiveData<List<NoteModel>>{
        return notes
    }

    fun getData(){
        db.collection("NoteApp")
            .get()
            .addOnSuccessListener { result ->
                val tempNotes = arrayListOf<NoteModel>()
                for (document in result) {
                    document.data.map { (key, value) -> tempNotes.add(NoteModel(document.id, value.toString())) }
                }
                notes.postValue(tempNotes)
            }
            .addOnFailureListener { exception -> Log.w("MainActivity", "Error", exception) }
    }

    fun addNote(note: NoteModel){
        CoroutineScope(Dispatchers.IO).launch {
            val newNote = hashMapOf(
                "noteText" to note.note,
            )
            db.collection("NoteApp").add(newNote)
            getData()
        }
    }

    fun editNote(noteID: String, noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            db.collection("NoteApp")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        println("DB: ${document.id}")
                        println("LOCAL: $noteID")
                        if(document.id == noteID){
                            db.collection("NoteApp").document(noteID).update("noteText", noteText)
                        }
                    }
                    getData()
                }
                .addOnFailureListener { exception -> Log.w("MainActivity", "Error", exception) }
        }
    }

    fun deleteNote(noteID: String){
        CoroutineScope(Dispatchers.IO).launch {
            db.collection("NoteApp")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        println("DB: ${document.id}")
                        println("LOCAL: $noteID")
                        if(document.id == noteID){
                            db.collection("notes").document(noteID).delete()
                        }
                    }
                    getData()
                }
                .addOnFailureListener { exception -> Log.w("MainActivity", "Error", exception) }
        }
    }
}