package com.example.xmlformat

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.net.URL

class FeedParser {
    private var text = ""
    var inEntry = false
    private var title = ""
    var currentRecord = FeedItem()

    private val TAG = "FeedParser"
    private val applications = ArrayList<FeedItem>()

    fun parse(xmlData: String): Boolean {
        var status = true
        var inEntry = false
        var textValue = ""

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())
            var eventType = xpp.eventType
            var currentRecord = FeedItem()
            while (eventType != XmlPullParser.END_DOCUMENT) {

                val tagName = xpp.name?.toLowerCase()
                when (eventType) {

                    XmlPullParser.START_TAG -> {
                        if (tagName == "entry") {
                            inEntry = true
                        }
                    }

                    XmlPullParser.TEXT -> textValue = xpp.text

                    XmlPullParser.END_TAG -> {
                        if (inEntry) {
                            when (tagName) {
                                "entry" -> {
                                    applications.add(currentRecord)
                                    inEntry = false
                                    currentRecord = FeedItem()   // create a new object
                                }

                                "name" -> currentRecord.name = textValue

                            }
                        }


                    }
                }

                eventType = xpp.next()

            }

        }catch(e: XmlPullParserException){
            e.printStackTrace()
            status = false
        }catch(e: IOException){
            e.printStackTrace()
            status = false
        }
        return status
    }
    fun getParsedList(): ArrayList<FeedItem> {

        return applications
    }
}

