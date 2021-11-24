package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var text : TextView
    val x = 10
    val y = 9
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // text = findViewById(R.id.textView)

    }


    fun show(view: View) {
      //  text.setText("$x + $y = ${sum(x,y)}")

    }
}