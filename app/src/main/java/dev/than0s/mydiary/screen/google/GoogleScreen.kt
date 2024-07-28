package dev.than0s.mydiary.screen.google

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dev.than0s.mydiary.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

@Composable
fun GoogleScreen(
    viewModel: GoogleViewModel = hiltViewModel(),
    oneTapClient: SignInClient = Identity.getSignInClient(LocalContext.current),
    restartApp: () -> Unit,
    isSignIn: Boolean
) {
    GoogleScreenContent(
        onResult = if (isSignIn) viewModel::authenticate else viewModel::linkAccount,
        restartApp = restartApp,
        oneTapClient = oneTapClient
    )
}

@Composable
fun GoogleScreenContent(
    onResult: (Intent, () -> Unit) -> Unit,
    restartApp: () -> Unit,
    oneTapClient: SignInClient
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let {
                    onResult(
                        it,
                        restartApp
                    )
                }
            }
        }
    )

    LaunchedEffect(key1 = Unit) {
        val signInIntentSender = signIn(oneTapClient, context)
        launcher.launch(
            IntentSenderRequest.Builder(
                signInIntentSender ?: return@LaunchedEffect
            ).build()
        )
    }
}

private suspend fun signIn(oneTapClient: SignInClient, context: Context): IntentSender? {
    val result = try {
        oneTapClient.beginSignIn(
            buildSignInRequest(context)
        ).await()
    } catch (e: Exception) {
        e.printStackTrace()
        if (e is CancellationException) throw e
        null
    }
    return result?.pendingIntent?.intentSender
}

private fun buildSignInRequest(context: Context): BeginSignInRequest {
    return BeginSignInRequest.Builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.web_client_id))
                .build()
        )
        .setAutoSelectEnabled(false)
        .build()
}