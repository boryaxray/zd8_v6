package com.example.zd5_v66

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTeacherActivity : AppCompatActivity(),
    PhotoPickerDialogFragment.OnPhotoSelectedListener {
    private lateinit var teacherDao: TeacherDao
    private lateinit var selectedPhotoImageView: ImageView
    private var selectedPhoto: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_teacher)

        val etTeacherFullName: EditText = findViewById(R.id.et_teacher_full_name)
        val etSpecialtyId: EditText = findViewById(R.id.et_specialty_id)
        val etWorkload: EditText = findViewById(R.id.et_workload)
        val btnSaveTeacher: Button = findViewById(R.id.btn_save_teacher)

        selectedPhotoImageView = findViewById(R.id.selected_photo_image_view1)
        teacherDao = CollegeDatabase.getDatabase(applicationContext).teacherDao()

        btnSaveTeacher.setOnClickListener {
            val teacherFullName = etTeacherFullName.text.toString()
            val specialtyId = etSpecialtyId.text.toString()
            val workload = etWorkload.text.toString().toInt()

            val teacher = Teacher(fullName = teacherFullName, specialty = specialtyId, workload = workload, photoPath = selectedPhoto!! )
            CoroutineScope(Dispatchers.IO).launch {
                teacherDao.insertTeacher(teacher)
                runOnUiThread {
                    Toast.makeText(this@AddTeacherActivity, "Преподаватель добавлен", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
        findViewById<Button>(R.id.button_select_photo1).setOnClickListener {
            val dialog = PhotoPickerDialogFragment()
            dialog.show(supportFragmentManager, "PhotoPicker")
        }
    }

    override fun onPhotoSelected(photo: String) {
        val resId = resources.getIdentifier(photo, "drawable", packageName)
        if (resId != 0) {
            selectedPhotoImageView.setImageResource(resId)
            selectedPhoto = resources.getResourceEntryName(resId)
        } else {
            Log.e("AddStudentsActivity", "Image resource not found: $photo")
        }
    }
}