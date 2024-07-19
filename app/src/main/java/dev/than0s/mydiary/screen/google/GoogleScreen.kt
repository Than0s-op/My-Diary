package dev.than0s.mydiary.screen.google

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GoogleScreen(
    viewModel: GoogleViewModel = hiltViewModel(),
    restartApp: () -> Unit,
    isSignIn: Boolean
) {
    GoogleScreenContent(
        onResult = if (isSignIn) viewModel::authenticate else viewModel::linkAccount,
        restartApp = restartApp,
        intentLauncher = viewModel::intentLauncher
    )
}

@Composable
fun GoogleScreenContent(
    onResult: (Intent, (String) -> Unit, () -> Unit) -> Unit,
    restartApp: () -> Unit,
    intentLauncher: (ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let {
                    onResult(
                        it,
                        { message ->
                            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                        },
                        restartApp
                    )
                }
            }
        }
    )


    intentLauncher(launcher)
}