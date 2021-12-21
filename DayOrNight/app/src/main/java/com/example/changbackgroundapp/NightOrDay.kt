package com.example.changbackgroundapp

import android.view.View
import kotlin.random.Random

class NightOrDay {

    companion object{
        val night = false
        val day = true
    }

    fun changeBackground(layout: View, choese: Boolean){
        val num = Random.nextInt(1,3)
        when {
            (choese == true && num == 1) -> {layout.setBackgroundResource(R.mipmap.n1)}
            (choese == false && num == 1) ->{layout.setBackgroundResource(R.mipmap.d1)}

            (choese == true && num == 2) -> {layout.setBackgroundResource(R.mipmap.n4)}
            (choese == false && num == 2) ->{layout.setBackgroundResource(R.mipmap.d2)}

            (choese == true && num == 3) -> {layout.setBackgroundResource(R.mipmap.n3)}
            (choese == false && num == 3) ->{layout.setBackgroundResource(R.mipmap.d3)}

        }

    }
}