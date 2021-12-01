package com.example.study

import android.app.Dialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class itemAdapter (val items:List<String>,val items2:List<String> ) : RecyclerView.Adapter<itemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textMassge: TextView
        var textMassge2: TextView
        init {
            textMassge = itemView.findViewById(R.id.textView2)
            textMassge2 = itemView.findViewById(R.id.textView3)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: itemAdapter.ViewHolder, position: Int) {
        holder.textMassge.text =  items[position]
        holder.textMassge2.text =  items2[position]

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}