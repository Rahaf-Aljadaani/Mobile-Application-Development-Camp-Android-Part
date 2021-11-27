package com.example.chatapp_recyclerviewapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class Recycler(private val messages: ArrayList<String>): RecyclerView.Adapter<Recycler.MessageViewHolder>() {

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textMassge: TextView

        init {
            textMassge = itemView.findViewById(R.id.text1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.list_mas,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
       // val message = messages[position]

        holder.textMassge.text =  messages[position]
     //   holder.itemView.apply {
      //      tvMessage.text = message
       // }
    }

    override fun getItemCount() = messages.size
}