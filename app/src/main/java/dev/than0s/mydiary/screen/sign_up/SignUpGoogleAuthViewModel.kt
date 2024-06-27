package dev.than0s.mydiary.screen.sign_up

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.than0s.mydiary.R
import dev.than0s.mydiary.model.service.GoogleAccountService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class SignUpGoogleAuthViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val oneTapClient: SignInClient,
    private val googleAccountService: GoogleAccountService
) : MyDiaryViewModel() {

    fun onResult(data: Intent) {
        viewModelScope.launch {
            googleAccountService.linkAccount(data)
        }
    }

    fun logIn(launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) {
        viewModelScope.launch {
            val signInIntentSender = signIn()
            launcher.launch(
                IntentSenderRequest.Builder(
                    signInIntentSender ?: return@launch
                ).build()
            )
        }
    }

    private suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}