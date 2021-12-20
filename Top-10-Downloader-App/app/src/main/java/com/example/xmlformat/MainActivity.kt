package com.example.xmlformat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL



class MainActivity : AppCompatActivity() {
    private lateinit var clRoot: ConstraintLayout
    lateinit var listOfText: ArrayList<FeedItem>
    lateinit var rvMain: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfText = ArrayList()

        clRoot = findViewById(R.id.clRoot)
        rvMain = findViewById(R.id.recacl)
        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = itemAdapter(listOfText)

    }

    fun getData(view: View) {
        request()
    }

    private fun request(){
        CoroutineScope(IO).launch {
            val rssFeed = async {

                downloadXML("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")

            }.await()
                val parseApplications = async {

                    FeedParser()

                }.await()
            parseApplications.parse(rssFeed)
            listOfText = parseApplications.getParsedList()
            try{
                withContext(Main){
                    rvMain.adapter!!.notifyDataSetChanged()
                }
            }catch(e: java.lang.Exception){
                Log.d("MAIN", "Error in get data")
            }
        }
    }

    private fun downloadXML(urlString: String?): String {
        val xmlResult = StringBuilder()

        try {
            val url = URL(urlString)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val inputBuffer = CharArray(500)
            var charsRead = 0
            while (charsRead >= 0) {
                charsRead = reader.read(inputBuffer)
                if (charsRead > 0) {
                    xmlResult.append(String(inputBuffer, 0, charsRead))
                }
            }
            reader.close()

            return xmlResult.toString()

        } catch (e: MalformedURLException) {
            Log.e("MAIN", "downloadXML: Invalid URL ${e.message}")
        } catch (e: IOException) {
            Log.e("MAIN", "downloadXML: IO Exception reading data: ${e.message}")
        } catch (e: SecurityException) {
            e.printStackTrace()
            Log.e("MAIN", "downloadXML: Security exception.  Needs permissions? ${e.message}")
        } catch (e: Exception) {
            Log.e("MAIN", "Unknown error: ${e.message}")
        }
        return ""
    }



}


//  rvMain.update(listOfText)