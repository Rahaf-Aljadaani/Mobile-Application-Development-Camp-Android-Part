package com.example.xml_parsing

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
import java.net.URL


data class Info(val name: String)

class MainActivity : AppCompatActivity() {

    private lateinit var clRoot: ConstraintLayout
    lateinit var listOfText: ArrayList<Info>
    lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfText = ArrayList()

        clRoot = findViewById(R.id.clRoot)
        rvMain = findViewById(R.id.recacl)
        rvMain.layoutManager = LinearLayoutManager(this)

        parserRSS()


    }

    private fun parserRSS() {
        CoroutineScope(IO).launch {
            val data = async {
                parser()
            }.await()
            try {
                withContext(Main){
                    rvMain.adapter = itemAdapter(listOfText)
                    rvMain.adapter!!.notifyDataSetChanged()
                }
            }catch (e : java.lang.Exception){
                Log.d("Main","unable to get data")
            }
        }
    }

    fun parser(): ArrayList<Info>{
        var name = ""
        var text = ""
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            val url = URL("https://timesofindia.indiatimes.com/rssfeeds/1945062111.cms")
            parser.setInput(url.openStream(),null)
            var eventype = parser.eventType
            while (eventype != XmlPullParser.END_DOCUMENT){
                val tagName = parser.name
                when(eventype){
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when (tagName){
                        "title" ->{
                            name = text.toString()
                            val data = Info(name)
                            listOfText.add(data)
                        }

                    }
                    else -> {}
                }
                eventype = parser.next()
            }

        }catch (e : XmlPullParserException){
            e.printStackTrace()
        }

        return listOfText
    }

}