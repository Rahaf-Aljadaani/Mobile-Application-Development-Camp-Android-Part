package com.example.xmlformat

class FeedItem {
    var name: String = ""


    override fun toString(): String {
        return """
            name = $name
           """.trimIndent()
    }
}
