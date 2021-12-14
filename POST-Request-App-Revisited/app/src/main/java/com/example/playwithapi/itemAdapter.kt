package com.example.playwithapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class itemAdapter (var items: ArrayList<Info>) : RecyclerView.Adapter<itemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textName: TextView
        var textLocation: TextView

        init {
            textName= itemView.findViewById(R.id.textView)
            textLocation= itemView.findViewById(R.id.textView2)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items_custom,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: itemAdapter.ViewHolder, position: Int) {
        holder.textName.text =  items[position].name
        holder.textLocation.text =  items[position].location

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(persons: InfoPerson){
        items = persons
        notifyDataSetChanged()
    }
}