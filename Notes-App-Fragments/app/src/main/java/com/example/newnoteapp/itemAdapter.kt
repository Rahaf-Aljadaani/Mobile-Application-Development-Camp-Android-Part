package com.example.newnoteapp


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class itemAdapter(val activity: first) : RecyclerView.Adapter<itemAdapter.ViewHolder>() {
    private var items = emptyList<NoteModel>()

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
            with(activity.sharedPreferences.edit()) {
                putString("NoteId", items[position].id)
                apply()
            }
            activity.findNavController().navigate(R.id.action_first_to_update)
            notifyDataSetChanged()
        }
        holder.deBut.setOnClickListener {
            activity.mainViewModel.deleteNote(items[position].id)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(notes: List<NoteModel>){
        println("UPDATING DATA")
        this.items = notes
        notifyDataSetChanged()
    }

}