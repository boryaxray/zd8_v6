package com.example.zd5_v66

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: Student): Long

    @Delete
    suspend fun deleteStudent(student: Student): Int

    @Update
    fun updateStudent(student: Student): Int

    @Query("SELECT * FROM students WHERE fullName LIKE :name")
    suspend fun searchStudentsByName(name: String): MutableList<Student>

    @Query("SELECT * FROM students")
    suspend fun getAllStudentsByGroup(): MutableList<Student>

    @Query("DELETE FROM students WHERE fullName = :name")
    suspend fun deleteStudentByName(name: String): Int

    @Query("SELECT COUNT(*) FROM students WHERE group1 = :groupId")
    fun getStudentCountByGroup(groupId: String): Int

    @Query("SELECT COUNT(*) FROM students WHERE fullName = :fullName AND dateOfBirth = :dateOfBirth")
    fun countStudentsByNameAndDOB(fullName: String, dateOfBirth: String): Int
}

@Dao
interface TeacherDao {
    @Insert
    suspend fun insertTeacher(teacher: Teacher)

    @Update
    suspend fun updateTeacher(teacher: Teacher)

    @Delete
    suspend fun deleteTeacher(teacher: Teacher)

    @Query("SELECT * FROM teachers WHERE fullName LIKE :name")
    suspend fun searchTeachersByName(name: String): MutableList<Teacher>

    @Query("SELECT * FROM teachers")
    suspend fun getAllTeachersBySpecialty(): MutableList<Teacher>
}