package com.example.playwithapi


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @POST("info/")
     fun addInfo(@Body infoData : Info): Call<Info>



    @GET("info/")
    fun getAllInfo(): Call<InfoPerson>
}

