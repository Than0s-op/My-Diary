package dev.than0s.mydiary.screen.sign_in

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.ScaffoldState
import dev.than0s.mydiary.model.service.EmailAccountService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val emailAccountService: EmailAccountService) :
    MyDiaryViewModel() {
    val signInCred = mutableStateOf(SignInCred())

    fun onEmailChange(email: String) {
        signInCred.value = signInCred.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        signInCred.value = signInCred.value.copy(password = password)
    }

    fun onSignInClick(restartApp: () -> Unit) {
        launchCatching(ScaffoldState::showSnackBar) {
            try {
                emailAccountService.authenticate(
                    signInCred.value.email,
                    signInCred.value.password
                )
                ScaffoldState.showSnackBar("Sign In Successfully")
                restartApp()
            } catch (e: Exception) {
                ScaffoldState.showSnackBar(e.message ?: "Unknown Error")
            }
        }
    }
}