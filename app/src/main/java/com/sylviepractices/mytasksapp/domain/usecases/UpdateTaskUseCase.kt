package com.sylviepractices.mytasksapp.domain.usecases

import com.sylviepractices.mytasksapp.data.TasksRepository
import com.sylviepractices.mytasksapp.domain.model.TaskModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val tasksRepository: TasksRepository
) {

    suspend operator fun invoke(taskModel: TaskModel){
        tasksRepository.update(taskModel)
    }

}