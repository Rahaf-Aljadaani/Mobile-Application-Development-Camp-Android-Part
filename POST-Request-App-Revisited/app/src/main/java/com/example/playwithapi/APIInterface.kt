package com.example.playwithapi


import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @POST("info/")
     fun addInfo(@Body infoData : Info): Call<Info>

    @GET("info/")
    fun getAllInfo(): Call<InfoPerson>

    @PUT("/test/{id}")
    fun updateUser(@Path("id") id: Int, @Body userData: Info): Call<InfoPerson>

    @DELETE("/test/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>
}

