package com.zinoview.tzrecipesapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzrecipesapp.R
import com.zinoview.tzrecipesapp.presentation.state.UiRecipeState

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private val recipes = ArrayList<UiRecipeState>()

    private companion object {
        private const val PROGRESS = 1
        private const val BASE = 2
        private const val FAILURE = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when(recipes[position]) {
            is UiRecipeState.Progress -> PROGRESS
            is UiRecipeState.Base -> BASE
            else -> FAILURE
        }
    }

    fun update(recipes: List<UiRecipeState>) {
        this.recipes.clear()
        this.recipes.addAll(recipes)
        notifyDataSetChanged()
    }

    abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        open fun bind(recipe: UiRecipeState) {

        }

        class Progress(view: View) : ViewHolder(view)

        class Base(view: View) : ViewHolder(view) {
            private val recipeImageView = view.findViewById<ImageView>(R.id.recipe_image)
            private val recipeTitleTextView = view.findViewById<TextView>(R.id.recipe_title_tv)
            private val recipeDescriptionTextView = view.findViewById<TextView>(R.id.recipe_description_tv)

            override fun bind(recipe: UiRecipeState) {
                recipe.mapView(recipeTitleTextView,recipeImageView,recipeDescriptionTextView)
            }
        }

        class Failure(view: View) : ViewHolder(view) {
            private val recipeFailureTextView = view.findViewById<TextView>(R.id.recipe_failure_tv)

            override fun bind(recipe: UiRecipeState) {
                recipe.mapView(recipeFailureTextView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            PROGRESS -> ViewHolder.Progress(R.layout.progress_item.makeView(parent))
            BASE -> ViewHolder.Base(R.layout.recipe_item.makeView(parent))
            else -> ViewHolder.Failure(R.layout.failure_item.makeView(parent))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    private fun Int.makeView(parent: ViewGroup) : View
        = LayoutInflater.from(parent.context).inflate(this,parent,false)
}