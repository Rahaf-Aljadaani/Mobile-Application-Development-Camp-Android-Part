package com.example.chatapp_recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var clRoot: ConstraintLayout
    lateinit var sendText: EditText
    lateinit var listOfText: ArrayList<String>
     lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        listOfText = ArrayList()
        clRoot = findViewById(R.id.clRoot)
        rvMain = findViewById(R.id.recacl)

        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = Recycler(listOfText)

        sendText = findViewById(R.id.text)
    }

    fun send(view: View) {
        val text = sendText.text.toString()
        if(!text.equals("")){
            listOfText.add(text)
            sendText.text.clear()
           rvMain.adapter!!.notifyDataSetChanged()
        }else{
            Snackbar.make(clRoot, "Please enter some text", Snackbar.LENGTH_LONG).show()
        }
    }
}