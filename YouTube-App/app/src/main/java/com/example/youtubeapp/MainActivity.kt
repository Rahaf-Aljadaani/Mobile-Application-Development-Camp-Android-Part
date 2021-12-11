package com.example.youtubeapp

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class viedoInfo(val name: String, val viedo_URL: String) {}

class MainActivity : AppCompatActivity() {
    private lateinit var clRoot: ConstraintLayout
    lateinit var listOfvidoe: ArrayList<viedoInfo>
    lateinit var rvMain: RecyclerView
    lateinit var youTubePlayerView: YouTubePlayerView
    lateinit var player: YouTubePlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfvidoe = ArrayList()
        listOfvidoe.add(viedoInfo("YouTube In Android Studio", "L0WGZSiOZsM"))
        listOfvidoe.add(viedoInfo("React Portfolio Website", "hQjlM-8C4Ps"))
        listOfvidoe.add(viedoInfo("Portfolio Website", "OPaLnMw2i_0"))

        clRoot = findViewById(R.id.clRoot)
        rvMain = findViewById(R.id.recacl)

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        activeNetwork?.isConnectedOrConnecting
        if (activeNetwork?.isConnectedOrConnecting == true) {

            youTubePlayerView = findViewById(R.id.youtube_player_view);
            getLifecycle().addObserver(youTubePlayerView);
            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    player = youTubePlayer
                    val videoId = "L0WGZSiOZsM"
                    player.loadVideo(videoId, 0f)
                    initialize()
                }
            })
        } else {
            println("Not Connected To The Internet")
            Toast.makeText(this, "Not Connected To The Internet", Toast.LENGTH_SHORT).show()
        }
        Log.d("MainActivity", "$$$$$$$$$$$$$$$$$$$$$$$")
        Log.d("MainActivity", "${listOfvidoe.size}")


    }

    private fun initialize() {
        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = itemAdapter(listOfvidoe, player)

    }


}

