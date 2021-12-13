package com.example.playwithapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIInfo {
    private var retrofit: Retrofit? = null

    fun getInfo(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}