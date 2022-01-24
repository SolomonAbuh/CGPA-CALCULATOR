package com.example.cgpacalculator

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val context:Context, var courseList: ArrayList<ResultModel>):RecyclerView
.Adapter<RecyclerViewAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater.from(context).inflate(R.layout.item_card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val currentCourse = courseList[position]

        if (position%2 == 0){
            holder.view.setBackgroundColor(Color.WHITE)
        }

        holder.courseTitle.text = currentCourse.courseTitle.uppercase()
        holder.courseUnit.text = currentCourse.unit.toString()
        holder.caScore.text = currentCourse.caScore.toString()
        holder.examScore.text = currentCourse.examScore.toString()

        holder.removeBtn.setOnClickListener {
            deleteItem(holder.adapterPosition)
        }

    }
    fun deleteItem(itemPosition: Int){
        courseList.removeAt(itemPosition)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
       return courseList.size
    }

    class ViewHolder(item: View):RecyclerView.ViewHolder(item){
        val courseTitle = item.findViewById<TextView>(R.id.course_title)
        val courseUnit = item.findViewById<TextView>(R.id.course_unit)
        val caScore = item.findViewById<TextView>(R.id.ca_score)
        val examScore = item.findViewById<TextView>(R.id.exam_score)
        val removeBtn = item.findViewById<ImageView>(R.id.remove)
        val view = item.findViewById<LinearLayout>(R.id.main_back)
    }
}