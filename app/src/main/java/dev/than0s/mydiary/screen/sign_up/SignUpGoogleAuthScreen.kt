package dev.than0s.mydiary.screen.sign_up

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GoogleAuth(viewModel: SignUpGoogleAuthViewModel = hiltViewModel()) {
    GoogleAuthContent(viewModel)
}

@Composable
fun GoogleAuthContent(viewModel: SignUpGoogleAuthViewModel) {
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