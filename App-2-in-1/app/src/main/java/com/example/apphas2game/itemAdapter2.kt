package com.example.apphas2game
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class itemAdapter2 (val items: ArrayList<String>) : RecyclerView.Adapter<itemAdapter2.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textMassge: TextView

        init {
            textMassge = itemView.findViewById(R.id.textView2)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemAdapter2.ViewHolder {
        return itemAdapter2.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items2,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: itemAdapter2.ViewHolder, position: Int) {
        holder.textMassge.text =  items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

}