package com.trungdunghoang125.foodapp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {

    @GET("random.php")
    fun getRandomFood(): Call<FoodList>

    @GET("lookup.php")
    fun getFoodDetail(@Query("i") i: String): Call<FoodList>
}