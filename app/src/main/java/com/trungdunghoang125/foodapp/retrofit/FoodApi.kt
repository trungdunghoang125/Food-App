package com.trungdunghoang125.foodapp.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface FoodApi {

    @GET("random.php")
    fun getRandomFood(): Call<FoodList>
}