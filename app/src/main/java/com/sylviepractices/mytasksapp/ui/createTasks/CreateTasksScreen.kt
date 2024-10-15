package com.sylviepractices.mytasksapp.ui.createTasks

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sylviepractices.mytasksapp.domain.model.TaskModel

@Composable
fun CreateTaskScreen(modifier: Modifier, viewModel: CreateTasksViewModel) {

    val showDialog: Boolean by viewModel.showDialog.observeAsState(initial = false)

    Box(modifier = modifier.fillMaxSize()) {
        CreateTaskDialog(
            showDialog,
            onDismiss = { viewModel.dialogClose() },
            onTaskAdded = { viewModel.onTaskCreated(it) })
        FabDialog(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            viewModel = viewModel
        )
        TasksList(viewModel = viewModel)
    }
}

@Composable
fun FabDialog(modifier: Modifier, viewModel: CreateTasksViewModel) {
    FloatingActionButton(
        modifier = modifier,
        onClick = { viewModel.onShowDialogClick() }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "")
    }
}

@Composable
fun CreateTaskDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {

    var myTask by remember {
        mutableStateOf("")
    }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Agrega tu tarea",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    onTaskAdded(myTask)
                    myTask = ""
                }) {
                    Text(text = "Agregar tarea")
                }
            }
        }
    }

}

@Composable
fun TasksList(viewModel: CreateTasksViewModel) {

    val myTasks: List<TaskModel> = viewModel.tasks

    LazyColumn {
        items(myTasks, key = { it.id }) { task ->
            TaskItem(task = task, viewModel = viewModel)
        }
    }
}

@Composable
fun TaskItem(task: TaskModel, viewModel: CreateTasksViewModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    viewModel.deleteTask(task)
                })
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(12.dp),
        border = BorderStroke(width = 2.dp, color = Color.Black)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp), text = task.task
            )
            Checkbox(
                checked = task.selected,
                onCheckedChange = { viewModel.onCheckBoxSelected(task) })
        }
    }


}
