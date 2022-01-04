package com.example.noteapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class itemAdapter( val activity: MainActivity,val items: ArrayList<NoteModel>) :
    RecyclerView.Adapter<itemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var linearLayout: LinearLayout
        var upBut: ImageView
        var deBut: ImageView
        var textMassge: TextView

        init {
            textMassge = itemView.findViewById(R.id.textView)
            upBut = itemView.findViewById(R.id.updaetIcon)
            deBut = itemView.findViewById(R.id.deletIcon)
            linearLayout = itemView.findViewById(R.id.line)
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
        holder.textMassge.text = items[position].note


        if (position % 2 == 0) {
            holder.linearLayout.setBackgroundColor(Color.CYAN)
        }
        holder.upBut.setOnClickListener {
            activity.raiseDialog(items[position].id)
        }
        holder.deBut.setOnClickListener {
            activity.deleteNote(items[position].id)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}