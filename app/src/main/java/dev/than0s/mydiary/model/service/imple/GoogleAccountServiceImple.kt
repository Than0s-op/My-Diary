package dev.than0s.mydiary.model.service.imple

import android.content.Intent
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dev.than0s.mydiary.model.service.GoogleAccountService
import dev.than0s.mydiary.screen.settings.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class GoogleAccountServiceImple @Inject constructor(
    private val oneTapClient: SignInClient,
    private val auth: FirebaseAuth
) : GoogleAccountService {

    private suspend fun signInWithIntent(
        intent: Intent,
        onCredentials: (AuthCredential) -> Task<AuthResult>
    ) {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        onCredentials(googleCredentials).await()
    }


    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val isAnonymous:Boolean
        get() = auth.currentUser!!.isAnonymous

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let { User(it.uid, it.isAnonymous) } ?: User())
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun authenticate(data: Intent) {
        signInWithIntent(
            intent = data,
            auth::signInWithCredential
        )
    }

    override suspend fun linkAccount(data: Intent) {
        signInWithIntent(
            intent = data,
            auth.currentUser!!::linkWithCredential
        )
    }

    override suspend fun createAnonymousAccount() {
        auth.signInAnonymously().await()
    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }
}