package com.example.todolist

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var clRoot: ConstraintLayout
    lateinit var rvMain: RecyclerView
    var listOfText = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfText = ArrayList()
        clRoot = findViewById(R.id.clRoot)
        rvMain = findViewById(R.id.recacl)
        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = itemAdapter(listOfText)
    }

    fun add(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("To Do")
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dilog_custom, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout)
        builder.setNeutralButton("Add") { dialog, which ->
            Toast.makeText(applicationContext, "Add", Toast.LENGTH_SHORT).show()
            listOfText.add(editText.text.toString())
            editText.text.clear()
            rvMain.adapter!!.notifyDataSetChanged()
        }

        builder.setPositiveButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext, android.R.string.no, Toast.LENGTH_SHORT).show()
        }

        builder.show()
    }

}