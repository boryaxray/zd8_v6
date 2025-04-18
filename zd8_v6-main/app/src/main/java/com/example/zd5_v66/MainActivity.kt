package com.example.zd5_v66

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddStudent: Button = findViewById(R.id.btn_add_student)
        val btnViewStudents: Button = findViewById(R.id.btn_view_students)
        val btnAddTeacher: Button = findViewById(R.id.btn_add_teacher)
        val btnStatistics: Button = findViewById(R.id.btn_statistics)

        btnAddStudent.setOnClickListener {
            startActivity(Intent(this, AddStudentsActivity::class.java))
        }
        btnViewStudents.setOnClickListener {
            startActivity(Intent(this, ViewStudentsActivity::class.java))
        }
        btnAddTeacher.setOnClickListener {
            startActivity(Intent(this, AddTeacherActivity::class.java))
        }
        btnStatistics.setOnClickListener {
            startActivity(Intent(this, StatisticsActivity::class.java))
        }
    }
}