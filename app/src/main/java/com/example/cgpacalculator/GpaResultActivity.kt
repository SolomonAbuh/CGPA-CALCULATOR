package com.example.cgpacalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GpaResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val gpaTextView = findViewById<TextView>(R.id.gpa_tv)

        gpaTextView.text = "Your gpa is ${intent.getStringExtra("gpa")}"

    }
}