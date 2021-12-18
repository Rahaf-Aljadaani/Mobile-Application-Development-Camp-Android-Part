package com.example.xmlformat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException


data class Info(val id: Int, val name: String, val grade: Float)

class MainActivity : AppCompatActivity() {
    private lateinit var clRoot: ConstraintLayout
    lateinit var listOfText: ArrayList<Info>
    lateinit var rvMain: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfText = ArrayList()

        clRoot = findViewById(R.id.clRoot)
        rvMain = findViewById(R.id.recacl)
        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = itemAdapter(listOfText)
        parseXML()
    }

    private fun parseXML(){
        try{
            val parser = XmlParser()
            val iStream = assets.open("student_details.xml")
            listOfText = parser.parse(iStream)
            rvMain.adapter!!.notifyDataSetChanged()

        }catch(e: IOException){
            println("ISSUE: $e")
        }
    }

}


//  rvMain.update(listOfText)