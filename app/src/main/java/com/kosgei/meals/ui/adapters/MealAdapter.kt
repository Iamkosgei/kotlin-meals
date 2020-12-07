package com.kosgei.meals.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kosgei.meals.R
import com.kosgei.meals.data.model.Meal


class MealAdapter(private val meals: ArrayList<Meal>) : RecyclerView.Adapter<MealAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(meal: Meal) {
            itemView.apply {
                findViewById<TextView>(R.id.mealName).text =meal.name
                Glide.with(findViewById<ImageView>(R.id.mealImage).context)
                    .load(meal.image)
                    .into( findViewById<ImageView>(R.id.mealImage))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.meal_item, parent, false))

    override fun getItemCount(): Int = meals.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    fun addMeals(meals: List<Meal>) {
        this.meals.apply {
            clear()
            addAll(meals)
        }

    }
}