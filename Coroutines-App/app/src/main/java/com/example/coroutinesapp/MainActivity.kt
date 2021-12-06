package com.example.coroutinesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
   lateinit var text : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.textView)

    }

    private fun requestAPI(){
        CoroutineScope(IO).launch {
            // we fetch the data
            val data = async { fetchData()}.await()
            if(data.isNotEmpty()){
                populateRV(data)
            }else{
                Log.d("MAIN", "Unable to get data")
            }
        }
    }

    private fun fetchData(): String{
        var response = ""
        try{
            response = URL("https://api.adviceslip.com/advice").readText()
        }catch(e: Exception){
            Log.d("MAIN", "ISSUE: $e")
        }
        return response
    }

    private suspend fun populateRV(result: String){
        withContext(Main){
            val json = JSONObject(result)
            val make = json.getJSONObject("slip").getString("advice")
            println("MAKE: $make")
            text.text = make
        }
    }

    fun adv(view: View) {
        requestAPI()
    }
}