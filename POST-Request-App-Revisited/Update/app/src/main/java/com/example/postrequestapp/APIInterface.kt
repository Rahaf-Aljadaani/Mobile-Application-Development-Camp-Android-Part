package com.example.postrequestapp

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @POST("test/")
    fun addInfo(@Body infoData : Info): Call<Info>

    @GET("test/")
    fun getAllInfo(): Call<InfoPerson>

    @PUT("/test/{id}")
    fun updateUser(@Path("id") id: Int, @Body userData: Info): Call<Info>

    @DELETE("/test/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>
}