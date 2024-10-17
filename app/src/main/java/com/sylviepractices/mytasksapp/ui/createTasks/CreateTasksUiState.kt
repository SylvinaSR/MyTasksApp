package com.sylviepractices.mytasksapp.ui.createTasks

import com.sylviepractices.mytasksapp.domain.model.TaskModel

sealed interface CreateTasksUiState {

    data object Loading: CreateTasksUiState
    data class Error(val throwable: Throwable): CreateTasksUiState
    data class Success(val tasks: List<TaskModel>): CreateTasksUiState

}