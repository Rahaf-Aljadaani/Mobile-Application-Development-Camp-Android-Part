package com.example.buttonapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
  lateinit var textView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
    }

    fun add(view: View) {
        var num = Integer.parseInt(textView.getText() as String)
        num++
        when{
            num == 0 -> textView.setTextColor(Color.parseColor("black"))
            (num%2 == 0) -> textView.setTextColor(Color.parseColor("green"))
            else -> textView.setTextColor(Color.parseColor("red"))
        }
        textView.setText("${num}")

    }
    fun sub(view: View) {

        var num = Integer.parseInt(textView.getText() as String)
        num--
        when{
            num == 0 -> textView.setTextColor(Color.parseColor("black"))
            (num%2 == 0) -> textView.setTextColor(Color.parseColor("green"))
            else -> textView.setTextColor(Color.parseColor("red"))
        }
        textView.setText("${num}")
    }
}