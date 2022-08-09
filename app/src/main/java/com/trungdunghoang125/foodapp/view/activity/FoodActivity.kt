package com.trungdunghoang125.foodapp.view.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.trungdunghoang125.foodapp.databinding.ActivityFoodBinding
import com.trungdunghoang125.foodapp.view.fragment.HomeFragment
import com.trungdunghoang125.foodapp.viewModel.FoodViewModel

class FoodActivity : AppCompatActivity() {
    lateinit var binding: ActivityFoodBinding
    lateinit var foodId: String
    lateinit var foodName: String
    lateinit var foodThumb: String
    lateinit var viewModel: FoodViewModel
    lateinit var youtubeUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get value for viewModel
        viewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        getContentInformation()
        updateInformation()

        // query food with id i
        viewModel.getFoodDetail(foodId)
        observerFoodDetailLiveData()

        // Redirecting to youtube
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
            startActivity(intent)
        }
    }

    private fun getContentInformation() {
        foodId = intent.getStringExtra(HomeFragment.FOOD_ID).toString()
        foodName = intent.getStringExtra(HomeFragment.FOOD_NAME).toString()
        foodThumb = intent.getStringExtra(HomeFragment.FOOD_THUMB).toString()
    }

    private fun updateInformation() {
        Glide.with(this)
            .load(foodThumb)
            .into(binding.imgCollapsingToolbar)
        binding.collapsingToolbar.title = foodName
    }

    private fun observerFoodDetailLiveData() {
        viewModel.observerFoodDetailLiveData().observe(this, Observer { newValue ->
            binding.tvCategories.text = "Category: ${newValue.strCategory}"
            binding.tvArea.text = "Area: ${newValue.strArea}"
            binding.tvFoodInstructions.text = newValue.strInstructions
            youtubeUrl = newValue.strYoutube
        })
    }
}