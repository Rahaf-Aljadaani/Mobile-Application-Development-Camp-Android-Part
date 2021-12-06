package com.example.calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var text : TextView
    var num1 = 0.0
    var num2 = 0.0
    var operatin = ""
    var number = ""
    var antherNum = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.textView)
    }

    fun add9(view: View) {
        number = "${number}9"
        text.text =" ${text.text}9"
    }
    fun add8(view: View) {
        number = "${number}8"
        text.text =" ${text.text}8"
    }
    fun add7(view: View) {
        number = "${number}7"
        text.text =" ${text.text}7"
    }
    fun add6(view: View) {
        number = "${number}6"
        text.text =" ${text.text}6"
    }
    fun add5(view: View) {
        number = "${number}5"
        text.text = " ${text.text}5"
    }
    fun add4(view: View) {
        number = "${number}4"
        text.text =" ${text.text}4"
    }
    fun add3(view: View) {
        number = "${number}3"
        text.text =" ${text.text}3"
    }
    fun add2(view: View) {
        number = "${number}2"
        text.text =" ${text.text}2"
    }
    fun add1(view: View) {
        number = "${number}1"
        text.text =" ${text.text}1"
    }
    fun add0(view: View) {
        number = "${number}0"
        text.text =" ${text.text}0"
    }
    fun dot(view: View) {
        number = "${number}."
        text.text =" ${number}"
    }
    fun sign(view: View) {
        if(number.toDouble()>0){
            number = "-${number}"
        }
        else{
            if(number.startsWith("-")){
                number.substring(1, number.length)
            }
        }
        text.text =" ${number}"
    }
    fun del(view: View) {
        text.text = text.text.substring(0,text.text.length-1)
        number = number.substring(0,number.length-1)
    }
    fun c(view: View) {
        text.text =""
        num1 = 0.0
        num2 = 0.0
        number = ""
    }
    fun add(view: View) {
        addNumber(number.toDouble())
        text.text =" ${number} +"
        number = ""
        operatin = "+"

    }
    fun sub(view: View) {
        addNumber(number.toDouble())
        text.text =" ${number} -"
        number = ""
        operatin = "-"
    }
    fun mult(view: View) {
        addNumber(number.toDouble())
        text.text =" ${number} *"
        number = ""
        operatin = "*"

    }
    fun diff(view: View) {
        addNumber(number.toDouble())
        text.text =" ${number} /"
        number = ""
        operatin = "/"

    }
    fun resalt(view: View) {
        addNumber(number.toDouble())
        num1 = solve()
        num2 = 0.0
        number = ""
        text.text =" $num1"
    }

   fun addNumber( x : Double){
       when(0.0){
           num1 -> num1 = x
           num2 -> num2 = x
       }
       text.text =" ${text.text} ${x.toInt()}"
    }
    fun solve () : Double{
        when(operatin) {
            "+"-> return (num1 + num2)
            "-"-> return (num1 - num2)
            "/"-> {
                if(num2==0.0){
                    Toast.makeText(this , "Cannot diff by 0 ", Toast.LENGTH_SHORT).show()
                    return 0.0}
                return (num1 / num2)
            }
            "*"-> return (num1 * num2)
        }
        return 0.0
    }

}