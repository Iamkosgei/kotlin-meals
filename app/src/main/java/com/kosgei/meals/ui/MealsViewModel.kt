package com.kosgei.meals.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kosgei.meals.data.repository.MealsRepository
import com.kosgei.meals.utils.Resource
import kotlinx.coroutines.Dispatchers

class MealsViewModel(private val mealsRepository: MealsRepository) : ViewModel() {

    fun getMeals() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
          var  data = mealsRepository.getMeals()

            emit(Resource.success(data = data))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}