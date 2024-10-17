package com.sylviepractices.mytasksapp.ui.createTasks

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylviepractices.mytasksapp.domain.model.TaskModel
import com.sylviepractices.mytasksapp.domain.usecases.AddTaskUseCase
import com.sylviepractices.mytasksapp.domain.usecases.DeleteTaskUseCase
import com.sylviepractices.mytasksapp.domain.usecases.GetTasksUseCase
import com.sylviepractices.mytasksapp.domain.usecases.UpdateTaskUseCase
import com.sylviepractices.mytasksapp.ui.createTasks.CreateTasksUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    getTasksUseCase: GetTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val logTAG = CreateTasksViewModel::class.java.simpleName

    val uiState: StateFlow<CreateTasksUiState> = getTasksUseCase().map(::Success)
        .catch { CreateTasksUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CreateTasksUiState.Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

//    private val _tasks = mutableStateListOf<TaskModel>()
//    val tasks: List<TaskModel> = _tasks

    fun dialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        _showDialog.value = false
        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(task: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(task.copy(selected = !task.selected))
        }
    }

    fun deleteTask(task: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }

}