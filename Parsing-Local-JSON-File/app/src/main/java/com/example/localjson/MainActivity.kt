package com.example.localjson

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException

data class Image(var title: String, var link: String)

class MainActivity : AppCompatActivity() {
    private lateinit var images: ArrayList<Image>
    lateinit var recycler: RecyclerView
    private lateinit var rvAdapter: itemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        images = arrayListOf()
        recycler = findViewById(R.id.rvMain)

        recycler.adapter = itemAdapter(this, images)
        recycler.layoutManager = LinearLayoutManager(this)

        val jsonData = getDataFromAssets(this, "data.json")
        showPhotos(jsonData)

    }

    fun getDataFromAssets(context: Context, fileName: String): String {
        var jsonData = " "
        try {
            jsonData = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        return jsonData
    }

    private fun showPhotos(data: String) {
        val jsonObj = JSONArray(data)
        for (i in 0 until jsonObj.length()) {
            val title = jsonObj.getJSONObject(i).getString("title")
            val url = jsonObj.getJSONObject(i).getString("url")

            images.add(Image(title, url))
        }
    }
}