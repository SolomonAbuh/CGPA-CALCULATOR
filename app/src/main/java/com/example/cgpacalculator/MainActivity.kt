package com.example.cgpacalculator

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cgpacalculator.databinding.AddCourseDialogBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val courses = mutableListOf<ResultModel>()
        val adapter = RecyclerViewAdapter(this, courses as ArrayList<ResultModel>)
        val addBtn = findViewById<FloatingActionButton>(R.id.add_btn)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter


        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setTitle(null)

        val binding: AddCourseDialogBinding = AddCourseDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)


        addBtn.setOnClickListener {
            dialog.show()

        }

        binding.doneBtn.setOnClickListener {
            val course = ResultModel(
                binding.courseTitleEt.text.toString(),
                binding.courseUnitEt.text.toString().toInt(),
                binding.caScoreEt.text.toString().toFloat(),
                binding.examScoreEt.text.toString().toFloat()
            )
            courses.add(course)
            adapter.notifyItemInserted(courses.size - 1)
            dialog.dismiss()
        }

        val calcGpaBtn = findViewById<FloatingActionButton>(R.id.calc_gpa_btn)
        calcGpaBtn.setOnClickListener {
            val intent = Intent(this, GpaResultActivity::class.java)
            intent.putExtra("gpa", computeGpa(courses).toString())
            startActivity(intent)
        }

    }

    private fun computeGpa(coursesResults: List<ResultModel>): Float {
        var unitMultiGradePoint = 0f
        var totalUnit = 0f
        for (courses in coursesResults) {
            unitMultiGradePoint += courses.unit * gradePoint(courses.caScore + courses.examScore)
            totalUnit += courses.unit
        }
        return (unitMultiGradePoint / totalUnit)
    }

    private fun gradePoint(score: Float): Int {
        var gradePoint = 0
        when {
            score >= 70 -> gradePoint = 5
            score >= 60 -> gradePoint = 4
            score >= 50 -> gradePoint = 3
            score >= 45 -> gradePoint = 2
        }
        return gradePoint
    }
}
