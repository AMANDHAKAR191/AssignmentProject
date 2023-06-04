package com.aman.assignmentproject

import PermissionScreen
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aman.assignmentproject.screens.Screen
import com.aman.assignmentproject.ui.theme.AssignmentProjectTheme


class MainActivity : ComponentActivity() {
    private val SENT = "SMS_SENT"
    private val DELIVERED = "SMS_DELIVERED"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentProjectTheme {
                // A surface container using the 'background' color from the theme
                registerReceiver(sentReceiver, IntentFilter(SENT))
                registerReceiver(deliveredReceiver, IntentFilter(DELIVERED))
                MainUI()
            }
        }
    }

    @Composable
    private fun MainUI() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.PermissionScreen.route
            ) {
                composable(Screen.PermissionScreen.route) {
                    PermissionScreen(navController)
                }
                composable(Screen.HomeScreen.route) {
                    //write code to pass values from HomeScreen to NextScreen
                    HomeScreen(navController)
                }
                composable("${Screen.NextScreen.route}/{name}/{location}/{mobileNo}/{email}") { backStackEntry ->
                    val name = backStackEntry.arguments?.getString("name")
                    val location = backStackEntry.arguments?.getString("location")
                    val mobileNo = backStackEntry.arguments?.getString("mobileNo")
                    val email = backStackEntry.arguments?.getString("email")
                    NextScreen(name, location, mobileNo, email,
                        onClick = {
                            Toast.makeText(this@MainActivity, "sending message", Toast.LENGTH_SHORT)
                                .show()
                            sendSMS(mobileNo, "Hello")
                        })
                }
                composable(Screen.AboutScreen.route) {
                    AboutScreen()
                }
                composable(Screen.DetailsScreen.route) {
                    DetailsScreen()
                }
            }
        }
    }

    private val sentReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (resultCode) {
                RESULT_OK -> Toast.makeText(context, "SMS sent", Toast.LENGTH_SHORT).show()
                SmsManager.RESULT_ERROR_GENERIC_FAILURE -> Toast.makeText(
                    context,
                    "Generic failure",
                    Toast.LENGTH_SHORT
                ).show()

                SmsManager.RESULT_ERROR_NO_SERVICE -> Toast.makeText(
                    context,
                    "No service",
                    Toast.LENGTH_SHORT
                ).show()

                SmsManager.RESULT_ERROR_NULL_PDU -> Toast.makeText(
                    context,
                    "Null PDU",
                    Toast.LENGTH_SHORT
                ).show()

                SmsManager.RESULT_ERROR_RADIO_OFF -> Toast.makeText(
                    context,
                    "Radio off",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private val deliveredReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (resultCode) {
                RESULT_OK -> Toast.makeText(context, "SMS delivered", Toast.LENGTH_SHORT).show()
                RESULT_CANCELED -> Toast.makeText(context, "SMS not delivered", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun sendSMS(phoneNumber: String?, message: String) {
        val sentPI = PendingIntent.getBroadcast(this, 0, Intent(SENT), PendingIntent.FLAG_IMMUTABLE)
        val deliveredPI = PendingIntent.getBroadcast(
            this,
            0,
            Intent(DELIVERED),
            PendingIntent.FLAG_IMMUTABLE
        )

        val smsManager: SmsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI)
    }

}


