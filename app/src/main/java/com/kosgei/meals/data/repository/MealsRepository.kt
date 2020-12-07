package com.kosgei.meals.data.repository

import com.kosgei.meals.data.api.ApiHelper


class MealsRepository(private val apiHelper: ApiHelper) {

    suspend fun getMeals() = apiHelper.getMeals()
}