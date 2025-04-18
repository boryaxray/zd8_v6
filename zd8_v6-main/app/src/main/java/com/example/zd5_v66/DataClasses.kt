package com.example.zd5_v66

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val fullName: String,
    val group1: String,
    val course: String,
    val specialty: String,
    val dateOfBirth: String,
    val photoPath: String
){
    override fun toString(): String {
        return "$id. $fullName, $group1, курс: $course. №Специальности: $specialty. Дата рождения: $dateOfBirth"
    }
}

@Entity(tableName = "teachers")
data class Teacher(
    @PrimaryKey(autoGenerate = true) val teacherId: Long = 0,
    val fullName: String,
    val specialty: String,
    val workload: Int,
    val photoPath: String
)

