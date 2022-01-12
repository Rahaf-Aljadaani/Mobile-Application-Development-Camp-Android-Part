package com.example.xmlparsing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlparsing.databinding.ItemRowBinding

class RVAdapter(private var students: List<Student>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val student = students[position]

        holder.binding.apply {
            tvName.text = student.name
            tvGrade.text = student.grade.toString()
        }
    }

    override fun getItemCount() = students.size

    fun update(students: List<Student>){
        this.students = students
        notifyDataSetChanged()
    }
}