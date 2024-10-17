package com.sylviepractices.mytasksapp.di

import android.content.Context
import androidx.room.Room
import com.sylviepractices.mytasksapp.data.TasksDatabase
import com.sylviepractices.mytasksapp.data.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesTasksDao(tasksDatabase: TasksDatabase): TaskDao{
        return tasksDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideTasksDatabase(@ApplicationContext appContext: Context): TasksDatabase {
        return Room.databaseBuilder(appContext, TasksDatabase::class.java, "TasksDatabase").build()
    }

}