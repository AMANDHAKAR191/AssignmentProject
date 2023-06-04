import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.PermissionChecker
import androidx.navigation.NavController
import com.aman.assignmentproject.screens.Screen

@Composable
fun PermissionScreen(navController: NavController) {
    var isSMSPermissionGranted by remember { mutableStateOf(false) }
    var requestPermission by remember { mutableStateOf(true) }
    val context = LocalContext.current

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        isSMSPermissionGranted = isGranted
        requestPermission = !isGranted
    }
    println("check1")

    DisposableEffect(key1 = requestPermission) {
        println("check2")
        if (requestPermission) {
            val permissionResult = PermissionChecker.checkSelfPermission(
                context, Manifest.permission.SEND_SMS
            )
            isSMSPermissionGranted = (permissionResult == PermissionChecker.PERMISSION_GRANTED)

            if (!isSMSPermissionGranted) {
                requestPermissionLauncher.launch(Manifest.permission.SEND_SMS)
            } else {
                requestPermission = false
            }
        }
        onDispose {  }
    }

    if (isSMSPermissionGranted) {
        navController.navigate(Screen.HomeScreen.route)
    } else {
        Text(text = "SMS Permission is $isSMSPermissionGranted \nSMS permission is required to verify your mobile no.")
    }
}

