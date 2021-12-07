package com.example.weatherapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
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
    lateinit var add: TextView
    lateinit var upd: TextView
    lateinit var stat: TextView
    lateinit var tempreter: TextView
    lateinit var low: TextView
    lateinit var high: TextView
    lateinit var sun: TextView
    lateinit var sun2: TextView
    lateinit var win: TextView
    lateinit var pre: TextView
    lateinit var hum: TextView
    var city = "10001"
    val API = "f8e92098868539ccc36acc0488b4e77c"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //

        add = findViewById<TextView>(R.id.address)
        upd = findViewById<TextView>(R.id.updated_at)
        stat = findViewById<TextView>(R.id.status)
        tempreter = findViewById<TextView>(R.id.temp)
        low = findViewById<TextView>(R.id.temp_min)
        high = findViewById<TextView>(R.id.temp_max)
        sun = findViewById<TextView>(R.id.sunrise)
        sun2 = findViewById<TextView>(R.id.sunset)
        win = findViewById<TextView>(R.id.wind)
        pre = findViewById<TextView>(R.id.pressure)
        hum = findViewById<TextView>(R.id.humidity)

        //
        requestAPI()
       // weatherTask().execute()
    }

    private fun requestAPI(){
        println("CITY: $city")
        CoroutineScope(IO).launch {
            //updateStatus(-1)
            val data = async {
                fetchWeatherData()
            }.await()
            if(data.isNotEmpty()){
                updateWeatherData(data)
             //  updateStatus(0)
            }else{
              //  updateStatus(1)
            }
        }
    }

    private suspend fun updateWeatherData(result: String){
        withContext(Main){
            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt: Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt * 1000))
                val temp = main.getString("temp") + "°C"
                val tempMin = "Low " + main.getString("temp_min") + "°C"
                val tempMax = "High: " + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name") + ", " + sys.getString("country")


                add.text = address
                upd.text = updatedAtText
                stat.text = weatherDescription.capitalize()
                tempreter.text = temp
                low.text = tempMin
                high.text = tempMax
                sun.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
                sun2.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
                win.text = windSpeed
                pre.text = pressure
                hum.text = humidity


            } catch (e: Exception) {
                println("Error : $e")
            }
        }
    }

    private fun fetchWeatherData(): String{
        var response = ""
        try {
            response = URL("https://api.openweathermap.org/data/2.5/weather?zip=$city&units=metric&appid=$API")
                    .readText(Charsets.UTF_8)
        }catch (e: Exception){
            errorMas()
            println("Error: $e")
        }
        return response
    }



    fun cheangCity(view: View) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dielog_custom, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout)
        builder.setPositiveButton("Refrsh") { dialog, which ->
            city = editText.text.toString()
            editText.text.clear()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
        }

        builder.show()
        requestAPI()
       // weatherTask().execute()
    }

    fun errorMas(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Wrong number : There is no city has this number")
        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext, android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}

/*

 inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?zip=$city&units=metric&appid=$API").readText(
                        Charsets.UTF_8
                )
            } catch (e: Exception) {
                errorMas()
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt: Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt * 1000))
                val temp = main.getString("temp") + "°C"
                val tempMin = "Low " + main.getString("temp_min") + "°C"
                val tempMax = "High: " + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name") + ", " + sys.getString("country")


                add.text = address
                upd.text = updatedAtText
                stat.text = weatherDescription.capitalize()
                tempreter.text = temp
                low.text = tempMin
                high.text = tempMax
                sun.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
                sun2.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
                win.text = windSpeed
                pre.text = pressure
                hum.text = humidity


            } catch (e: Exception) {
                println("Error : $e")
            }

        }
    }
 */
