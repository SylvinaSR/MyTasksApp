package com.sylviepractices.mytasksapp.ui.createTasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Preview(showSystemUi = true)
@Composable
fun CreateTasksPreview() {
    CreateTaskScreen(modifier = Modifier.padding(PaddingValues(0.dp)))
}

@Composable
fun CreateTaskScreen(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        CreateTaskDialog(true, onDismiss = {}, onTaskAdded = {})
        FabDialog(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )

    }
}

@Composable
fun FabDialog(modifier: Modifier) {
    FloatingActionButton(
        modifier = modifier,
        onClick = { /*TODO*/ }) {
        //Mostrar dialogo
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
                }) {
                    Text(text = "Agregar tarea")
                }
            }
        }
    }

}
