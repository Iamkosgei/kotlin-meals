package com.kosgei.meals.data.api

import com.kosgei.meals.data.api.RetrofitBuilder.apiService

class ApiHelper {
    suspend fun getMeals() = apiService.getMeals()
}