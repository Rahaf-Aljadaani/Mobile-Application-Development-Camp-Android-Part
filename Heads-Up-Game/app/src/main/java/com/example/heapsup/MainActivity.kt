package com.example.heapsup

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Surface
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var text: TextView
    lateinit var time: TextView
    lateinit var name: TextView
    lateinit var taboo1: TextView
    lateinit var taboo2: TextView
    lateinit var taboo3: TextView
    lateinit var celebrity: ArrayList<JSONObject>
var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        text = findViewById(R.id.textv)
        name = findViewById(R.id.name)
        taboo1 = findViewById(R.id.taboo1)
        taboo2 = findViewById(R.id.taboo2)
        taboo3 = findViewById(R.id.taboo3)
        celebrity = arrayListOf()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val rotation = windowManager.defaultDisplay.rotation
        if (rotation == Surface.ROTATION_0) {
            text.setVisibility(View.VISIBLE)
            text.text = "Plz Rotate the device"
            name.setVisibility(View.GONE)
            taboo1.setVisibility(View.GONE)
            taboo2.setVisibility(View.GONE)
            taboo3.setVisibility(View.GONE)


                if(id < celebrity.size){
                    id++
                    name.text = celebrity[id].getString("name")
                    taboo1.text = celebrity[id].getString("taboo1")
                    taboo2.text = celebrity[id].getString("taboo2")
                    taboo3.text = celebrity[id].getString("taboo3")
                }


        } else if (rotation == Surface.ROTATION_180) {
            text.setVisibility(View.GONE)
            name.setVisibility(View.VISIBLE)
            taboo1.setVisibility(View.VISIBLE)
            taboo2.setVisibility(View.VISIBLE)
            taboo3.setVisibility(View.VISIBLE)
            timer()
        }

    }

    fun timer() {
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time.text = "Time: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                time.text = "Time: --"
                text.text = "Heads Up!"
                taboo3.setVisibility(View.VISIBLE)

            }
        }.start()
    }

    fun start(view: View) {
        button.setVisibility(View.GONE)
        text.text = "Plz Rotate the device"
        requestAPI()
    }


    private fun requestAPI(){
        CoroutineScope(IO).launch {
            val data = async {
                getCelebrities()
            }.await()
            if(data.isNotEmpty()){
                withContext(Main){
                    parseJSON(data)
                    timer()
                }
            }
        }
    }

    private suspend fun parseJSON(result: String){
        withContext(Dispatchers.Main){
            celebrity.clear()
            val jsonArray = JSONArray(result)
            for(i in 0 until jsonArray.length()){
                celebrity.add(jsonArray.getJSONObject(i))
            }
        }
    }

    private fun getCelebrities(): String{
        var response = ""
        try {
            response = URL("https://dojo-recipes.herokuapp.com/celebrities/")
                .readText(Charsets.UTF_8)
        }catch (e: Exception){
            println("Error: $e")
        }
        return response
    }

}