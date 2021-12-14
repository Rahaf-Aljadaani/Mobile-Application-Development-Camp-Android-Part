package com.example.recipeapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private var recipes: List<Recipe>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name: TextView
        var author: TextView
        var ingredients: TextView
        var instructions: TextView

        init {
            name = itemView.findViewById(R.id.Title)
            author = itemView.findViewById(R.id.Author)
            ingredients = itemView.findViewById(R.id.Ingredients)
            instructions = itemView.findViewById(R.id.Instructions)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.name.text = recipe.title
        holder.author.text = recipe.author
        holder.ingredients.text = recipe.ingredients
        holder.instructions.text = recipe.instructions

    }

    override fun getItemCount() = recipes.size

    fun update(recipes: List<Recipe>){
        this.recipes = recipes
        notifyDataSetChanged()
    }
}