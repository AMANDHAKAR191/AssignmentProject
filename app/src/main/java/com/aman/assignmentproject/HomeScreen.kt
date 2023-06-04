package com.aman.assignmentproject

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aman.assignmentproject.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
        val context = LocalContext.current
        var name by remember {
            mutableStateOf("")
        }
        var location by remember {
            mutableStateOf("")
        }
        var mobileNo by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var enableFromDetails by remember {
            mutableStateOf(false)
        }
        val (checkedState, onStateChange) = remember { mutableStateOf(false) }

        Text(text = "HomeScreen", style = MaterialTheme.typography.headlineLarge)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name") },
            placeholder = { Text(text = "Enter your name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text(text = "Location") },
            placeholder = { Text(text = "Enter your Location") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = mobileNo,
            onValueChange = { mobileNo = it },
            label = { Text(text = "Mobile No") },
            placeholder = { Text(text = "+91 00000-00000") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "xyz@gmaill.com") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .toggleable(
                    value = checkedState,
                    onValueChange = { onStateChange(!checkedState) },
                    role = Role.Checkbox
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = null // null recommended for accessibility with screenreaders
            )
            Text(
                text = "Agree",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        val annotatedString = buildAnnotatedString {
            val name = "name: $name\n"
            val location = "location: $location\n"
            val mobileNo = "mobile No: $mobileNo\n"
            val email = "email: $email\n"
            append(name)
            append(location)
            append(mobileNo)
            append(email)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedButton(
                onClick = {
                    if (checkedState) {
                        Toast.makeText(context, annotatedString, Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.wrapContentHeight()
            ) {
                Text(text = "Toast")
            }
            OutlinedButton(
                onClick = {
                    if (checkedState) {
                        enableFromDetails = !enableFromDetails
                    }
                },
                modifier = Modifier.wrapContentHeight()
            ) {
                Text(text = "Text")
            }
        }
        Button(
            onClick = {
                if (checkedState) {
                    navController.navigate("${Screen.NextScreen.route}/$name/$location/$mobileNo/$email")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp)
        ) {
            Text(text = "Next")
        }

        if (enableFromDetails) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = annotatedString)
            Spacer(modifier = Modifier.height(10.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextButton(
                onClick = {
                    if (checkedState) {
                        navController.navigate(Screen.AboutScreen.route)
                    }
                },
                modifier = Modifier.wrapContentHeight()
            ) {
                Text(text = "About")
            }
            TextButton(
                onClick = {
                    if (checkedState) {
                        navController.navigate(Screen.DetailsScreen.route)
                    }
                },
                modifier = Modifier.wrapContentHeight()
            ) {
                Text(text = "Details")
            }
        }
    }
}
