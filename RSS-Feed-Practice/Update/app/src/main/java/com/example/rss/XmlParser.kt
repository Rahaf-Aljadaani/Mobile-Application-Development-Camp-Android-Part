package com.example.rss


import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.net.URL

class XMLParser {
    private val questions = ArrayList<Info>()
    private var text: String? = null
    private var title = ""
    fun parse(inputStream: InputStream): ArrayList<Info> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("title", ignoreCase = true) -> {
                            title = text.toString()
                        }

                        tagName.equals("entry", ignoreCase = true) ->
                        { questions.add(Info(title))
                        }
                        else->{

                        }
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return questions
    }
}