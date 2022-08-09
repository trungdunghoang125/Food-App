package com.trungdunghoang125.foodapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.trungdunghoang125.foodapp.databinding.FragmentHomeBinding
import com.trungdunghoang125.foodapp.retrofit.Food
import com.trungdunghoang125.foodapp.view.activity.FoodActivity
import com.trungdunghoang125.foodapp.viewModel.HomeFragmentViewModel
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel
    lateinit var food: Food

    companion object {
        const val FOOD_ID = "com.trungdunghoang125.foodapp.foodID"
        const val FOOD_NAME = "com.trungdunghoang125.foodapp.foodName"
        const val FOOD_THUMB = "com.trungdunghoang125.foodapp.foodThumb"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // set value for viewModel
        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]

        viewModel.getRandomFood()
        viewModel.observerFoodLiveData().observe(viewLifecycleOwner, Observer { newValue ->
            Glide.with(this)
                .load(newValue!!.strMealThumb)
                .into(binding.imgRandomMeal)

            // set value of newValue (food object) to food (public object)
            this.food = newValue
        })

        // set onClickListener for random img
        binding.imgRandomMeal.setOnClickListener {
            val intent = Intent(this.context, FoodActivity::class.java)
            intent.putExtra(FOOD_ID, food.idMeal)
            intent.putExtra(FOOD_NAME, food.strMeal)
            intent.putExtra(FOOD_THUMB, food.strMealThumb)
            startActivity(intent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}