package com.trungdunghoang125.foodapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trungdunghoang125.foodapp.retrofit.Food
import com.trungdunghoang125.foodapp.retrofit.FoodList
import com.trungdunghoang125.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "tranmyle"

class HomeFragmentViewModel : ViewModel() {
    private var randomFoodLiveData = MutableLiveData<Food>()

    fun getRandomFood() {
        RetrofitInstance.api.getRandomFood().enqueue(object : Callback<FoodList> {
            override fun onResponse(call: Call<FoodList>, response: Response<FoodList>) {
                if (response.body() != null) {
                    val randomFood: Food = response.body()!!.meals[0]
                    Log.d(TAG, "Name of food is ${randomFood.strMeal}")
                    randomFoodLiveData.value = randomFood
                }
            }

            override fun onFailure(call: Call<FoodList>, t: Throwable) {
                Log.d(TAG, "The error message is ${t.message}")
            }
        })
    }

    fun observerFoodLiveData(): LiveData<Food> {
        return randomFoodLiveData
    }
}