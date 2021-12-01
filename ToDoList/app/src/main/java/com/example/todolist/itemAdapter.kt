package com.example.todolist


import android.app.Dialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class itemAdapter (val items:List<String> ) : RecyclerView.Adapter<itemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textMassge: TextView
        var check1: CheckBox
        init {
            textMassge = itemView.findViewById(R.id.textView)
            check1 = itemView.findViewById(R.id.checkBox)
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

        holder.itemView.setOnClickListener {
            if(holder.check1.isChecked){
                holder.textMassge.setTextColor(Color.GRAY)
            }
            else{
                holder.textMassge.setTextColor(Color.BLACK)
            }
            holder.check1.isChecked = !holder.check1.isChecked


        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}