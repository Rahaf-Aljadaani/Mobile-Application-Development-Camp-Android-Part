package com.example.app_cycler

import android.content.Context
import android.content.SharedPreferences
import android.icu.number.NumberRangeFormatter.with
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Global.putString
import android.provider.Settings.Secure.getString
import android.view.Gravity.apply
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var clRoot: ConstraintLayout
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var sendText: EditText
    lateinit var listOfText: ArrayList<String>
    lateinit var rvMain: RecyclerView
    lateinit var but: Button
    var chars = ArrayList<Char>()
    var charsGussed = ArrayList<String>()
    lateinit var Textview: TextView
    lateinit var TextviewGesss: TextView
    lateinit var TextviewScoure: TextView
    var repet = 0
    var GusseText = "Hi iam Rahaf"
    var GusseTextStar = "** *** *****"

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendText = findViewById(R.id.textView)
        listOfText = ArrayList()

        clRoot = findViewById(R.id.clRoot)
        rvMain = findViewById(R.id.recacl)

        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = itemAdapter(listOfText)
        Textview = findViewById(R.id.textView3)
        TextviewGesss = findViewById(R.id.textView4)
        TextviewScoure = findViewById(R.id.textView5)
        but = findViewById(R.id.button)

        Textview.text = GusseTextStar
        charsGussed.add("Char Guessed : ")

        lodeData()

    }


    fun guess(view: View) {
        val text = sendText.text.toString()
        check(text)
        sendText.text.clear()
        rvMain.adapter!!.notifyDataSetChanged()
        saveData()
    }

    private fun check(text: String) {

        val chars = GusseText.toCharArray()

        if (repet <= 10 && !text.equals("")) {
            repet++
            listOfText.add("You have guess $text")
            when {
                text.length == 1 -> {
                    if (checkLetterGussen(text)) {
                        listOfText.add("You can't guess again with same leter")
                        listOfText.add("You Have ${10 - repet} gusses left")
                        return
                    }
                    var j = 0
                    var find = false
                    for (i in chars) {
                        if (i.toLowerCase() == text[0].toLowerCase()) {
                            GusseTextStar = GusseTextStar.substring(
                                0,
                                j
                            ) + text[0] + GusseTextStar.substring(j + 1)
                            find = true
                            listOfText.add("Right guess, $GusseTextStar")
                            listOfText.add("You Have ${10 - repet} gusses left")
                            charsGussed.add(text)
                        }
                        j++
                    }
                    if (!find) {
                        listOfText.add("Wrong guess, try again")
                        listOfText.add("You Have ${10 - repet}  gusses left")
                        charsGussed.add(text)
                    }
                    Textview.text = GusseTextStar
                }
                GusseText.equals(text) -> {

                    listOfText.add("You get it!")
                    listOfText.add("Game is over!")
                    charsGussed.add(text)
                }
                else -> {
                    listOfText.add("Wrong guess, try again")
                    listOfText.add("You Have ${10 - repet}  gusses left")
                    charsGussed.add(text)
                }
            }


        } else if (repet > 10) {

            listOfText.add("The answer is $GusseText")
            listOfText.add("Game is over!")
        } else {
            Snackbar.make(clRoot, "Please enter some letter", Snackbar.LENGTH_LONG).show()
        }

        var text = ""
        for (i in charsGussed) {
            text += " $i"
        }
        TextviewGesss.text = text
    }


    fun checkLetterGussen(ch: String): Boolean {
        for (i in charsGussed) {
            if (ch[0].toLowerCase() == i[0].toLowerCase()) {
                return true
            }
        }
        return false
    }

    fun saveData() {
        TextviewScoure.text = "Scoure: $repet"
        val sharedPreferences = getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("STRING_KEY","Scoure: $repet")
          //  putBoolean("BOOLEAN_KEY",sw_switch.isChecked)
        }.apply()
        Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
    }

    fun lodeData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)
        val savedBoolean = sharedPreferences.getBoolean("BOOLEAN_KEY",false)
        TextviewScoure.text = savedString
        //sw_switch = savedBoolean
    }
}

