package dev.than0s.mydiary.screen.sign_up.google

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dev.than0s.mydiary.screen.log_in.GoogleAuthContent

@Composable
fun GoogleScreen(
    viewModel: GoogleViewModel = hiltViewModel(),
    restartApp: () -> Unit
) {
    GoogleScreenContent(
        onResult = viewModel::onResult,
        restartApp = restartApp,
        intentLauncher = viewModel::intentLauncher
    )
}

@Composable
fun GoogleScreenContent(
    onResult: (Intent, () -> Unit) -> Unit,
    restartApp: () -> Unit,
    intentLauncher: (ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) -> Unit
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
    intentLauncher(launcher)
}