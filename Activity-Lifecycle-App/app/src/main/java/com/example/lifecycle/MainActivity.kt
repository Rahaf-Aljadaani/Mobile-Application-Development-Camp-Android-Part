package com.example.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", ">> I'm in onCreate fun ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", ">> I'm in onStart fun ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", ">> I'm in onResume fun ")
   }
    override fun onPause(){
        super.onPause()
        Log.d("MainActivity", ">> I'm in onPause fun ")
    }

    override fun  onStop(){
        super.onStop()
        Log.d("MainActivity", ">> I'm in onStop fun ")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d("MainActivity", ">> I'm in onDestroy fun ")
    }
}