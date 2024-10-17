package com.sylviepractices.mytasksapp.domain.usecases

import com.sylviepractices.mytasksapp.data.TasksRepository
import com.sylviepractices.mytasksapp.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val tasksRepository: TasksRepository
) {

    operator fun invoke(): Flow<List<TaskModel>> = tasksRepository.tasks

}