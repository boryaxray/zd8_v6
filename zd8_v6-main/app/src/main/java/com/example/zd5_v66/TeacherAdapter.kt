package com.example.zd5_v66

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TeacherAdapter(private val teachers: List<Teacher>, private val itemClickListener: (Int) -> Unit) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val teacherName: TextView = view.findViewById(R.id.tvTeacherName)
        val specialty: TextView = view.findViewById(R.id.tvTeacherSpecialty)
        val workload:TextView = view.findViewById(R.id.tvTeacherWorkload)
        val photoImageView: ImageView = itemView.findViewById(R.id.photo_image_view1)
        init {
            itemView.setOnClickListener {
                itemClickListener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_teacher, parent, false)
        return TeacherViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teachers[position]
        holder.teacherName.text = teacher.fullName
        holder.specialty.text = teacher.specialty
        holder.workload.text = teacher.workload.toString()

        val resId = holder.itemView.context.resources.getIdentifier(teacher.photoPath, "drawable", holder.itemView.context.packageName)
        if (resId != 0) {
            holder.photoImageView.setImageResource(resId)
        } else {
            holder.photoImageView.setImageResource(R.drawable.defoult)
        }
    }

    override fun getItemCount(): Int {
        return teachers.size
    }
}