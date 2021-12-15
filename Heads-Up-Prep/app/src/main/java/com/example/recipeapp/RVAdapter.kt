package com.example.recipeapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private var recipes: List<Celebrity>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name: TextView
        var tabo1: TextView
        var tabo2: TextView
        var tabo3: TextView

        init {
            name = itemView.findViewById(R.id.Title)
            tabo1 = itemView.findViewById(R.id.Tabo1)
            tabo2 = itemView.findViewById(R.id.Tabo2)
            tabo3 = itemView.findViewById(R.id.Tabo3)
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
        holder.name.text = recipe.name
        holder.tabo1.text = recipe.taboo1
        holder.tabo2.text = recipe.taboo2
        holder.tabo3.text = recipe.taboo3

    }

    override fun getItemCount() = recipes.size

    fun update(recipes: List<Celebrity>){
        this.recipes = recipes
        notifyDataSetChanged()
    }
}