package com.kosgei.meals.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kosgei.meals.data.api.ApiHelper
import com.kosgei.meals.data.repository.MealsRepository
import com.kosgei.meals.ui.MealsViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealsViewModel::class.java)) {
            return MealsViewModel(MealsRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}