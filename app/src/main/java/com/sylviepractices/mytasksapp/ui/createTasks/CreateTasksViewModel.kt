package com.sylviepractices.mytasksapp.ui.createTasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CreateTasksViewModel @Inject constructor() : ViewModel() {

    private val logTAG = CreateTasksViewModel::class.java.simpleName

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun dialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        Log.d(logTAG, "My new Task: $task")
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

}