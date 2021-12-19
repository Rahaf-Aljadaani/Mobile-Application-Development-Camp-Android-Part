package com.example.xmlformat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class itemAdapter (val items: ArrayList<Info>) : RecyclerView.Adapter<itemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textMassge: TextView
        var textMassge1: TextView

        init {
            textMassge = itemView.findViewById(R.id.textView)
            textMassge1 = itemView.findViewById(R.id.textView1)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: itemAdapter.ViewHolder, position: Int) {
        holder.textMassge.text =  items[position].title
        holder.textMassge1.text =  items[position].description

    }

    override fun getItemCount(): Int {
        return items.size
    }

}