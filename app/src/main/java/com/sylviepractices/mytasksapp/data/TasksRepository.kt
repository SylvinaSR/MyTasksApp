package com.sylviepractices.mytasksapp.data

import com.sylviepractices.mytasksapp.data.database.dao.TaskDao
import com.sylviepractices.mytasksapp.data.database.entity.TaskEntity
import com.sylviepractices.mytasksapp.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepository @Inject constructor(private val taskDao: TaskDao) {

    val tasks: Flow<List<TaskModel>> = taskDao.getTasks().map { items ->
        items.map {
            TaskModel(
                id = it.id,
                task = it.task,
                selected = it.selected
            )
        }
    }

    suspend fun add(taskModel: TaskModel) {
        taskDao.addTask(taskModel.toTaskEntity())
    }

    suspend fun update(taskModel: TaskModel) {
        taskDao.updateTask(taskModel.toTaskEntity())
    }

    suspend fun delete(taskModel: TaskModel) {
        taskDao.deleteTask(taskModel.toTaskEntity())
    }

}

fun TaskModel.toTaskEntity(): TaskEntity = TaskEntity(
    id = this.id,
    task = this.task,
    selected = this.selected
)