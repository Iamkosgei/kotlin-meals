package com.kosgei.meals.data.api

import com.kosgei.meals.data.model.Meals
import retrofit2.http.GET


interface ApiService {
    @GET("api/json/v1/1/filter.php?c=chicken")
    suspend fun getMeals(): Meals

}