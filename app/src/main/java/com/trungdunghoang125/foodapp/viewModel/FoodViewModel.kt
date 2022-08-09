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

class FoodViewModel : ViewModel() {
    val foodDetailLiveData = MutableLiveData<Food>()

    fun getFoodDetail(i: String) {
        RetrofitInstance.api.getFoodDetail(i).enqueue(object : Callback<FoodList> {
            override fun onResponse(call: Call<FoodList>, response: Response<FoodList>) {
                if (response.body() != null) {
                    val food: Food = response.body()!!.meals.get(0)
                    foodDetailLiveData.value = food
                } else return
            }

            override fun onFailure(call: Call<FoodList>, t: Throwable) {
                Log.d("FoodViewModel", t.message.toString())
            }

        })
    }

    fun observerFoodDetailLiveData(): LiveData<Food> {
        return foodDetailLiveData
    }
}