package com.example.app_cycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
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

    var repet = 0
    var randomNum = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendText = findViewById(R.id.textView)
        listOfText = ArrayList()

        clRoot = findViewById(R.id.clRoot)
        rvMain = findViewById(R.id.recacl)

        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = itemAdapter(listOfText)

        but = findViewById(R.id.button)


        randomNum =  Random().nextInt(11) //from 0 to 10

    }

   fun guess(view: View) {
        val text = sendText.text.toString()
        if(!text.equals("")){
           listOfText.add("You have guess $text")

            if(repet > 3){
                listOfText.add("The answer is $randomNum")
                listOfText.add("Game is over!")
            }

            check(text)

          sendText.text.clear()
          rvMain.adapter!!.notifyDataSetChanged()
       }else{
        Snackbar.make(clRoot, "Please enter some number", Snackbar.LENGTH_LONG).show()
     }
   }



    private fun check ( num : String) {
        if (repet<=3 ){
                when(Integer.parseInt(num.toString())){
                    randomNum -> {
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