package com.example.app_cycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var clRoot: ConstraintLayout

    lateinit var sendText: EditText
    lateinit var listOfText: ArrayList<String>
    lateinit var rvMain: RecyclerView
    lateinit var but : Button

    lateinit var Textview: TextView
    var repet = 0
    var GusseText = "Hi iam Rahaf"
    var GusseTextStar = "** *** *****"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendText = findViewById(R.id.textView)
        listOfText = ArrayList()

        clRoot = findViewById(R.id.clRoot)
        rvMain = findViewById(R.id.recacl)

        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = itemAdapter(listOfText)
        Textview =  findViewById(R.id.textView3)
        but = findViewById(R.id.button)

        Textview.text = GusseTextStar
    }

   fun guess(view: View) {


        val text = sendText.text.toString()
        if(!text.equals("")){
           listOfText.add("You have guess $text")

            if(repet > 10){
                listOfText.add("The answer is $GusseText")
                listOfText.add("Game is over!")
            }
            if (text.length == 1){
              for (i in 0..GusseText.length) {
                  if(GusseText.get(i).equals(text)){
                     GusseTextStar = GusseTextStar.substring(0, i) + text + GusseTextStar.substring(i + 1)
                  }
              }
                Textview.text = GusseTextStar
            }
            else{
            check(text)
            }

          sendText.text.clear()
          rvMain.adapter!!.notifyDataSetChanged()
       }else{
        Snackbar.make(clRoot, "Please enter some number", Snackbar.LENGTH_LONG).show()
     }
   }



    private fun check ( text : String) {
        if (repet<=10){
                when{
                    GusseText.equals(text) -> {
                        listOfText.add("You get it!")
                        listOfText.add("Game is over!")
                    }
                    else ->  {
                        listOfText.add ("Wrong guess, try again")
                        listOfText.add ("You Have $repet gusses left")
                    }
                }
                repet++

        }
    }
}