package com.example.flickrapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

data class Image(var title: String, var link: String)


class MainActivity : AppCompatActivity() {
    private lateinit var images: ArrayList<Image>
    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: itemAdapter

    private lateinit var linearLayoutForSearch: LinearLayout
    private lateinit var searchAbout: EditText
    private lateinit var btSearch: Button

    private lateinit var ivMain: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        images = arrayListOf()

        rvMain = findViewById(R.id.rvMain)
        rvAdapter = itemAdapter(this ,images)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        linearLayoutForSearch = findViewById(R.id.llBottom)
        searchAbout = findViewById(R.id.etSearch)
        btSearch = findViewById(R.id.btSearch)

        ivMain = findViewById(R.id.ivMain)
        ivMain.setOnClickListener { closeImg() }
    }


    fun search(view: View) {
        if (searchAbout.text.isNotEmpty()) {
            requestAPI()
        } else {
            Toast.makeText(this, "Search field is empty", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestAPI() {
        CoroutineScope(IO).launch {
            val data = async { getPhotos() }.await()
            if (data.isNotEmpty()) {
                println(data)
                showPhotos(data)
            } else {
                Toast.makeText(this@MainActivity, "No Images Found", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getPhotos(): String {
        var response = ""
        try {
            response = URL("https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=b633234b90a533d1cc0024e856f5c361&tags=${searchAbout.text}&format=json&nojsoncallback=1&auth_token=72157720827688829-99ea1ec2c7971eaf&api_sig=771934fdc0f1f7298b19d11a208d4912").readText(Charsets.UTF_8)
        } catch (e: Exception) {
            println("ISSUE: $e")
        }
        return response
    }

    private suspend fun showPhotos(data: String) {
        withContext(Main) {
            val jsonObj = JSONObject(data)
            val photos = jsonObj.getJSONObject("photos").getJSONArray("photo")
            println("photos")
            println(photos.getJSONObject(0))
            println(photos.getJSONObject(0).getString("farm"))
            for (i in 0 until photos.length()) {
                val title = photos.getJSONObject(i).getString("title")
                val serverID = photos.getJSONObject(i).getString("server")
                val id = photos.getJSONObject(i).getString("id")
                val secret = photos.getJSONObject(i).getString("secret")
                val photoLink = "https://live.staticflickr.com/$serverID/${id}_${secret}.jpg"
                images.add(Image(title, photoLink))
            }
            rvAdapter.notifyDataSetChanged()
        }
    }

    fun openImg(link: String) {
        Glide.with(this).load(link).into(ivMain)
        ivMain.isVisible = true
        rvMain.isVisible = false
        linearLayoutForSearch.isVisible = false
    }

    private fun closeImg() {
        ivMain.isVisible = false
        rvMain.isVisible = true
        linearLayoutForSearch.isVisible = true
    }

}