package com.example.noteapp
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository
    private val notes: LiveData<List<NoteModel>>

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = Repository(noteDao)
        notes = repository.getNotes
    }

    fun getNotes(): LiveData<List<NoteModel>>{
        return notes
    }

    fun addNote(noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addNote(NoteModel(0, noteText))
        }
    }

    fun editNote(noteID: Int, noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateNote(NoteModel(noteID,noteText))
        }
    }

    fun deleteNote(noteID: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteNote(NoteModel(noteID,""))
        }
    }
}