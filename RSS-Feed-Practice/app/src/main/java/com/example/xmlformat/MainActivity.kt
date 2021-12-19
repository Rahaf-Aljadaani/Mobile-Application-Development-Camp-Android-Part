package com.example.xmlformat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


data class Info(val title: String, val description: String)

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
        CoroutineScope(IO).launch {
            val data = async {
                val parser = XmlParser()
                parser.parse()
            }.await()
            try{
                withContext(Main){
                    rvMain.adapter!!.notifyDataSetChanged()
                }
            }catch(e: java.lang.Exception){
                Log.d("MAIN", "Error in get data")
            }
        }
    }

}


//  rvMain.update(listOfText)