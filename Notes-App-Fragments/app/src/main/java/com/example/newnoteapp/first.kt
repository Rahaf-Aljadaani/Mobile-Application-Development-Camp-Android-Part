package com.example.newnoteapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class first : Fragment() {
    private lateinit var rvNotes: RecyclerView
    private lateinit var noteAdapter: itemAdapter
    private lateinit var note: EditText

    lateinit var mainViewModel: MainViewModel
    private lateinit var AllNotes: List<NoteModel>
    private lateinit var addBut: Button

    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        AllNotes = arrayListOf()

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getNotes().observe(viewLifecycleOwner, {
                notes -> noteAdapter.update(notes) })

        note = view.findViewById(R.id.noteText)
        addBut = view.findViewById(R.id.addBut)
        addBut.setOnClickListener {
            mainViewModel.addNote(NoteModel("", note.text.toString()))
            note.text.clear()
        }

        rvNotes = view.findViewById(R.id.recy)
        noteAdapter = itemAdapter(this)
        rvNotes.adapter = noteAdapter
        rvNotes.layoutManager = LinearLayoutManager(requireContext())

        mainViewModel.getData()

        return view
    }


    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            mainViewModel.getData()
        }
    }
}