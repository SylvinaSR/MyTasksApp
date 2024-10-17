package com.sylviepractices.mytasksapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sylviepractices.mytasksapp.data.database.dao.TaskDao
import com.sylviepractices.mytasksapp.data.database.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {
    //Dao
    abstract fun taskDao(): TaskDao
}