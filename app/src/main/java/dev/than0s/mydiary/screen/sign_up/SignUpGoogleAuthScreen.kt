package dev.than0s.mydiary.screen.sign_up

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GoogleAuth(
    viewModel: SignUpGoogleAuthViewModel = hiltViewModel(),
    restartApp: (String) -> Unit
) {
    GoogleAuthContent(
        onResult = viewModel::onResult,
        restartApp = restartApp,
        logIn = viewModel::logIn
    )
}

@Composable
fun GoogleAuthContent(
    onResult: (Intent, (String) -> Unit) -> Unit,
    restartApp: (String) -> Unit,
    logIn: (ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let {
                    onResult(it, restartApp)
                }
            }
        }
    )
    logIn(launcher)
}