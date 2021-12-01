package com.example.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var clRoot: ConstraintLayout
    lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listOfText = listOf("String", "Boolean", "Integer", "Double","String", "Boolean", "Integer", "Double")
        val listOfText2 = listOf(
            "To hold text and character", "To hold true of false",
            "To hold integer numbers 1 ,2,3", "To hold double numbers 1.5 , 1.2 ,1.3",
            "To hold text and character", "To hold true of false",
            "To hold integer numbers 1 ,2,3", "To hold double numbers 1.5 , 1.2 ,1.3"
        )
        clRoot = findViewById(R.id.clRoot)
        rvMain = findViewById(R.id.recacl)
        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = itemAdapter(listOfText,listOfText2)
    }
}