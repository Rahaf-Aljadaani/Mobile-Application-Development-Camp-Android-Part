package com.example.app_cycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class itemAdapter (val items: ArrayList<String>) : RecyclerView.Adapter<itemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textMassge: TextView

        init {
            textMassge = itemView.findViewById(R.id.textView2)
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
        holder.itemView.apply {
            if( items[position].startsWith("Right")){
                holder.textMassge.setTextColor(Color.GREEN)
            }
            else if ( items[position].startsWith("Wrong")){
                holder.textMassge.setTextColor(Color.RED)
            }
            else{
                holder.textMassge.setTextColor(Color.BLACK)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
