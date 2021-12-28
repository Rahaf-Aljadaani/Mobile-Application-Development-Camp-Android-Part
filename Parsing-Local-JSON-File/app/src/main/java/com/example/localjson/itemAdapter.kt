package com.example.localjson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

class itemAdapter (val activity: MainActivity,val items: ArrayList<Image>) : RecyclerView.Adapter<itemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       var textImage: TextView
        var image : ImageView

        init {
           textImage = itemView.findViewById(R.id.imageText)
            image = itemView.findViewById(R.id.ivpic)
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
        holder.textImage.text =  items[position].title
        Glide.with(activity).load( items[position].link).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}