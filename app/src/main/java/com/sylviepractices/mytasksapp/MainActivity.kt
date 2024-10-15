package com.sylviepractices.mytasksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sylviepractices.mytasksapp.ui.createTasks.CreateTaskScreen
import com.sylviepractices.mytasksapp.ui.createTasks.CreateTasksViewModel
import com.sylviepractices.mytasksapp.ui.theme.MyTasksAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val createTaskViewModel: CreateTasksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTasksAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CreateTaskScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
