package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val x = 10
        val y = 9
        Log.d("MainActivity", "$x + $y = ${sum(x,y)}")
        Log.d("MainActivity", "$x - $y = ${sub(x,y)}")
        Log.d("MainActivity", "$x / $y = ${mult(x,y)}")
        Log.d("MainActivity", "$x * $y = ${diff(x,y)}")
        Log.d("MainActivity","> The End")
    }

    fun sum ( x : Int , y: Int ) : Double {
        return (x.toDouble()+y.toDouble())
    }
    fun sub ( x : Int , y: Int ) : Double {
        return (x.toDouble()-y.toDouble())
    }
    fun mult (x: Int, y: Int ) : Double {
        return (x.toDouble()*y.toDouble())
    }
    fun diff ( x : Int , y: Int ) : Double {
        return (x.toDouble()/y.toDouble())
    }
}