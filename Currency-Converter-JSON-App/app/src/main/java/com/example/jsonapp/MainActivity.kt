package com.example.jsonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var text: TextView
    lateinit var resalt: TextView
    lateinit var num: EditText
    lateinit var ch1: RadioButton
    lateinit var ch2: RadioButton
    lateinit var ch3: RadioButton
    lateinit var ch4: RadioButton
    lateinit var ch5: RadioButton

    var Choce: String = ""
    lateinit var data: String
    lateinit var eroList: ArrayList<Double>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        eroList = ArrayList()
        text = findViewById(R.id.text2)
        resalt = findViewById(R.id.textView3)
        num = findViewById(R.id.editTextNumber)

        ch1 = findViewById(R.id.radioButton6)
        ch2 = findViewById(R.id.radioButton7)
        ch3 = findViewById(R.id.radioButton8)
        ch4 = findViewById(R.id.radioButton9)
        ch5 = findViewById(R.id.radioButton10)

        requestAPI()
    }

    private fun requestAPI() {
        CoroutineScope(IO).launch {
            val data = async {
                fetchWeatherData()
            }.await()
            if (data.isNotEmpty()) {
                updateWeatherData(data)
            } else {
            }
        }
    }

    private suspend fun updateWeatherData(result: String) {
        withContext(Main) {
            try {
                val jsonObj = JSONObject(result)
                Log.d("MainActivity", "$$$$$$$$$$$$$$$$$$$$$$$")
                Log.d("MainActivity", "$result")
                val sys = jsonObj.getJSONObject("eur")
                data = jsonObj.getString("date")
                text.text = "Date : $data"
                eroList.add(sys.getDouble("ada"))
                eroList.add(sys.getDouble("aed"))
                eroList.add(sys.getDouble("afn"))
                eroList.add(sys.getDouble("all"))
                eroList.add(sys.getDouble("amd"))


            } catch (e: Exception) {
                println("Error : $e")
            }
        }
    }

    private fun fetchWeatherData(): String {
        var response = ""
        try {
            response =
                URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/eur.json").readText(
                    Charsets.UTF_8
                )
        } catch (e: Exception) {
            println("Error: $e")
        }
        return response
    }

    fun convert(view: View) {
        when (Choce) {
            "ada" -> resalt.text = " = ${eroList.get(0) * num.text.toString().toDouble()} ada"
            "amd" -> resalt.text = " = ${eroList.get(1) * num.text.toString().toDouble()} amd "
            "all" -> resalt.text = " = ${eroList.get(2) * num.text.toString().toDouble()} all"
            "afn" -> resalt.text = " = ${eroList.get(3) * num.text.toString().toDouble()} afn"
            "aed" -> resalt.text = " = ${eroList.get(4) * num.text.toString().toDouble()} aed"
            else -> Toast.makeText(this, "Choses ", Toast.LENGTH_LONG).show()
        }
    }

    fun ada(view: View) {
        Choce = "ada"
    }

    fun amd(view: View) {
        Choce = "amd"
    }

    fun all(view: View) {
        Choce = "all"
    }

    fun afn(view: View) {
        Choce = "afn"
    }

    fun aed(view: View) {
        Choce = "aed"
    }


}
