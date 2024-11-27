package com.example.medimeet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(val list:List<StudentModel>): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    class StudentViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        val name: TextView =itemview.findViewById(R.id.tv1)
        val sur: TextView =itemview.findViewById(R.id.tv2)
        val dob: TextView =itemview.findViewById(R.id.tv3)
        val problem: TextView =itemview.findViewById(R.id.tv4)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            LayoutInflater.from(parent.context).
        inflate(R.layout.row,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.name.text=list[position].name
        holder.sur.text=list[position].sur
        holder.dob.text=list[position].dob
        holder.problem.text=list[position].problem
    }
}