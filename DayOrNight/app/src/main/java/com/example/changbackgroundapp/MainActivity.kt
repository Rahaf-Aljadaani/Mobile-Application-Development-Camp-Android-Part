package com.example.changbackgroundapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    lateinit var text : TextView
    lateinit var textEd : TextView
    lateinit var layout  : ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout = findViewById(R.id.layoutView)
        text = findViewById(R.id.text)
        textEd = findViewById(R.id.texted)
    }

    fun change(view: View) {
        var textData = text.text.toString()
        textData = textData.toLowerCase()
        when(textData){
            "day" ->{
                val ch = NightOrDay()
                ch.changeBackground(layout,NightOrDay.day)
                text.setTextColor(Color.WHITE)
            }
            "night" ->{
                val ch = NightOrDay()
                ch.changeBackground(layout,NightOrDay.night)
                text.setTextColor(Color.BLACK)
            }
        }
    }
}