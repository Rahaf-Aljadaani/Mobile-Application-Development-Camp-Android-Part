package com.example.xmlparsing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException


data class Student(val id: Int, val name: String, val grade: Float)

class MainActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    private lateinit var students: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        students = listOf()

        rvMain = findViewById(R.id.rvMain)

        lode()
        parseXML()
    }


    private fun lode() {
        rvAdapter = RVAdapter(students)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)
    }

    private fun parseXML() {
        try {
            val parser = XmlParser()
            val iStream = assets.open("student_details.xml")
            students = parser.parse(iStream)
            rvAdapter.update(students)
        } catch (e: IOException) {
            println("ISSUE: $e")
        }
    }
}