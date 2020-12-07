package com.kosgei.meals.data.model

import com.google.gson.annotations.SerializedName

data class Meals(
        @SerializedName("meals")
        var meals:  List<Meal>
)