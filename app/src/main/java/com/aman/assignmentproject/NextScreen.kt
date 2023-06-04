package com.aman.assignmentproject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NextScreen(
    name: String?,
    location: String?,
    mobileNo: String?,
    email: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
        var openDialog by remember { mutableStateOf(false) }

        Text(text = "NextScreen", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(onClick = { /*TODO*/ }, enabled = false) {
            Text(text = "name: $name", color = Color.Black)
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(onClick = { /*TODO*/ }, enabled = false) {
            Text(text = "Location: $location", color = Color.Black)
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(onClick = { openDialog = true }) {
            Text(text = "Mobile No.: $mobileNo")
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(onClick = { /*TODO*/ }, enabled = false) {
            Text(text = "Email: $email", color = Color.Black)
        }



        if (openDialog) {
            AlertDialog(
                onDismissRequest = { openDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        onClick()
                        openDialog = false
                    }) {
                        Text(text = "Ok")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { openDialog = false }) {
                        Text(text = "Dismiss")
                    }
                },
                title = {
                    Text(
                        text = "Alert"
                    )
                },
                text = { Text(text = "To send SMS to $mobileNo\n click on ok") },
                shape = AlertDialogDefaults.shape,
                tonalElevation = AlertDialogDefaults.TonalElevation
            )
        }

    }
}