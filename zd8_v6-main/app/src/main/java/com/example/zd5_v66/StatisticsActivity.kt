package com.example.zd5_v66

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StatisticsActivity : AppCompatActivity() {
    private lateinit var teacherDao: TeacherDao
    private lateinit var studentDao: StudentDao
    private lateinit var statisticsAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val lvStatistics: ListView = findViewById(R.id.lv_statistics)
        statisticsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        lvStatistics.adapter = statisticsAdapter

        studentDao = CollegeDatabase.getDatabase(applicationContext).studentDao()
        teacherDao = CollegeDatabase.getDatabase(applicationContext).teacherDao()

        loadStatistics()
    }

    private fun loadStatistics() {
        CoroutineScope(Dispatchers.IO).launch {
            val studentCount = studentDao.getAllStudentsByGroup().size
            val teacherCount = teacherDao.getAllTeachersBySpecialty().size
            val statistics = listOf(
                "Количество студентов: $studentCount",
                "Количество преподавателей: $teacherCount"
            )

            withContext(Dispatchers.Main) {
                statisticsAdapter.clear()
                statisticsAdapter.addAll(statistics)
                statisticsAdapter.notifyDataSetChanged()
            }
        }
    }
}