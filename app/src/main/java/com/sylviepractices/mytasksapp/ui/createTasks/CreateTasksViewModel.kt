package com.sylviepractices.mytasksapp.ui.createTasks

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sylviepractices.mytasksapp.domain.model.TaskModel
import javax.inject.Inject

class CreateTasksViewModel @Inject constructor() : ViewModel() {

    private val logTAG = CreateTasksViewModel::class.java.simpleName

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _tasks = mutableStateListOf<TaskModel>()
    val tasks: List<TaskModel> = _tasks

    fun dialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        Log.d(logTAG, "My new Task: $task")
        _tasks.add(TaskModel(task = task))
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(task: TaskModel) {
        Log.d(logTAG, "My selectedTask Task: $task")
        val index = _tasks.indexOf(task)
        _tasks[index] = _tasks[index].let {
            it.copy(selected = !it.selected)
        }
    }

}