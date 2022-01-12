package com.example.rss


import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


data class Info(val title: String)

class MainActivity : AppCompatActivity() {
    private lateinit var clRoot: ConstraintLayout
    lateinit var listOfText: ArrayList<Info>
    lateinit var rvMain: RecyclerView
    lateinit var rvAdapter: itemAdapter

    private var text = ""

    private var title = ""
    private var name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfText = ArrayList()

        clRoot = findViewById(R.id.clRoot)
        rvMain = findViewById(R.id.recacl)
        //rvMain.layoutManager = LinearLayoutManager(this)
       // rvAdapter = itemAdapter(listOfText)
     //   rvMain.adapter = rvAdapter
        FetchQuestions().execute()
    }

    private inner class FetchQuestions : AsyncTask<Void, Void, ArrayList<Info>>() {
        val parser = XMLParser()
        override fun doInBackground(vararg params: Void?): ArrayList<Info> {
            val url = URL("https://stackoverflow.com/feeds")
            val urlConnection = url.openConnection() as HttpURLConnection
            listOfText =
                urlConnection.inputStream?.let {
                    parser.parse(it)
                }
                        as ArrayList<Info>
            return listOfText
        }

        override fun onPostExecute(result: ArrayList<Info>?) {
            super.onPostExecute(result)
            rvMain.adapter = result?.let { itemAdapter(it) }
            rvMain.layoutManager = LinearLayoutManager(applicationContext)
        }

    }

}