package dev.than0s.mydiary.screen.log_in

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GoogleAuth(viewModel: LogInGoogleAuthViewModel = hiltViewModel()) {
    GoogleAuthContent(viewModel)
}

@Composable
fun GoogleAuthContent(viewModel: LogInGoogleAuthViewModel) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let {
                    viewModel.onResult(it)
                }
            }
        }
    )
    viewModel.logIn(launcher)
}