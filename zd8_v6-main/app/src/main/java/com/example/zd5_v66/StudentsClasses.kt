package com.example.zd5_v66

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Student::class, Teacher::class], version = 3)
abstract class CollegeDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun teacherDao(): TeacherDao

    companion object {
        @Volatile
        private var instance: CollegeDatabase? = null



        fun getDatabase(context: Context): CollegeDatabase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    CollegeDatabase::class.java,
                    "college_database"
                ).build()
                instance = db
                db
            }
        }
    }
}