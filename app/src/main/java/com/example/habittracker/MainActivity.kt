package com.example.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    var habitNameInput by remember { mutableStateOf("") }
    var savedHabitName by remember { mutableStateOf<String?>(null) }

    Column(modifier = modifier) {
        Text(text = "Hello, Habit Tracker 👋")
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { showDialog = true }) {
            Text(text = "Add Habit")
        }

        savedHabitName?.let { habit ->
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "New habit: $habit")
        }
    }

    if (showDialog) {
        val trimmed = habitNameInput.trim()
        val canSave = trimmed.isNotEmpty()

        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Add Habit") },
            text = {
                OutlinedTextField(
                    value = habitNameInput,
                    onValueChange = { habitNameInput = it },
                    placeholder = { Text("Habit name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (canSave) {
                            savedHabitName = trimmed
                            habitNameInput = ""
                            showDialog = false
                        }
                    },
                    enabled = canSave
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
