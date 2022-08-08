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
import com.trungdunghoang125.foodapp.view.activity.FoodActivity
import com.trungdunghoang125.foodapp.viewModel.HomeFragmentViewModel
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel

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
        })

        // set onClickListener for random img
        binding.imgRandomMeal.setOnClickListener {
            val intent = Intent(this.context, FoodActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}