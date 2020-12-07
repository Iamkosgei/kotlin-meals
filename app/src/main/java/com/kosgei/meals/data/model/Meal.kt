package com.kosgei.meals.data.model

import com.google.gson.annotations.SerializedName

data class Meal (
    @SerializedName("strMeal")
    val name:String,
    @SerializedName("strMealThumb")
    val image:String
)