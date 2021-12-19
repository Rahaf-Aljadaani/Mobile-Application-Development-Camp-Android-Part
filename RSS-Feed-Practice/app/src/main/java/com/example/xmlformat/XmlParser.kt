package com.example.xmlformat

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.net.URL

class XmlParser {
    private val writes = ArrayList<Info>()
    private var text = ""


    private var title = ""
    private var description = ""

    fun parse(): ArrayList<Info>{
        try{
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            val url = URL("https://stackoverflow.com/feeds")
            parser.setInput(url.openStream(), null)
            var event = parser.eventType
            while(event != XmlPullParser.END_DOCUMENT){
                val tagName = parser.name
                when(event){
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("title", true) -> title = text
                        tagName.equals("description", true) -> {
                            description = text
                            writes.add(Info(title, description))
                        }
                        else -> {}
                    }
                    else -> {}
                }
                event = parser.next()
            }
        }catch(e: XmlPullParserException){
            e.printStackTrace()
        }catch(e: IOException){
            e.printStackTrace()
        }
        return writes
    }
}