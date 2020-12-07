package com.kosgei.meals.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kosgei.meals.R
import com.kosgei.meals.data.api.ApiHelper
import com.kosgei.meals.data.model.Meal
import com.kosgei.meals.ui.adapters.MealAdapter
import com.kosgei.meals.ui.base.ViewModelFactory
import com.kosgei.meals.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MealsViewModel
    private lateinit var adapter: MealAdapter
    private lateinit var recyclerView:RecyclerView
    private lateinit var  progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
       viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelper())).get(MealsViewModel::class.java)
    }


    private fun setupUI() {
        recyclerView.layoutManager = GridLayoutManager(this,2)
        adapter = MealAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as GridLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getMeals().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { meal -> retrieveList(meal.meals) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(meals: List<Meal>) {
        adapter.apply {
            addMeals(meals)
            notifyDataSetChanged()
        }
    }
}