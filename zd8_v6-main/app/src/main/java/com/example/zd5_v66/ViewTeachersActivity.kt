package com.example.zd5_v66

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewTeachersActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var specialityEditText: EditText
    private lateinit var workloadEditTExt: EditText

    private lateinit var teacherDao: TeacherDao
    private lateinit var teacherAdapter: TeacherAdapter
    private var currentPosition: Int = -1
    private var teachers: MutableList<Teacher> = mutableListOf()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_teachers)

        val searchView: android.widget.SearchView = findViewById(R.id.search_view1)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view1)
        teacherDao = CollegeDatabase.getDatabase(applicationContext).teacherDao()
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadStudents()

        teacherAdapter = TeacherAdapter(teachers) { position ->

            currentPosition = position
            val selectedItem = teachers[position]

            nameEditText.setText(selectedItem.fullName)
            specialityEditText.setText(selectedItem.specialty)
            workloadEditTExt.setText(selectedItem.workload)

        }

        recyclerView.adapter = teacherAdapter

        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchStudents(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    loadStudents()
                }
                return true
            }
        })
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun loadStudents() {
        CoroutineScope(Dispatchers.IO).launch {
            val newTeacher = teacherDao.getAllTeachersBySpecialty()
            withContext(Dispatchers.Main) {
                teachers.clear()
                teachers.addAll(newTeacher)
                teacherAdapter.notifyDataSetChanged()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchStudents(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = teacherDao.searchTeachersByName("%$name%")
            withContext(Dispatchers.Main) {
                teachers.clear()
                teachers.addAll(result)
                teacherAdapter.notifyDataSetChanged()
            }
        }
    }
}