package com.example.apphas2game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.text_guess -> {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                true
            }
            R.id.number_guess-> {
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
                true
            }
            R.id.main-> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun goToActvity2(view: View) {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }
    fun goToActvity3(view: View) {
        val intent = Intent(this, MainActivity3::class.java)
        startActivity(intent)
    }
}