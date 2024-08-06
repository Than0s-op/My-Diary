package dev.than0s.mydiary.screen.google

import android.content.Intent
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.ScaffoldState
import dev.than0s.mydiary.model.service.GoogleAccountService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import javax.inject.Inject

@HiltViewModel
class GoogleViewModel @Inject constructor(
    private val googleAccountService: GoogleAccountService
) : MyDiaryViewModel() {

    fun linkAccount(data: Intent, restartApp: () -> Unit) {
        launchCatching(ScaffoldState::showSnackBar) {
            try {
                googleAccountService.linkAccount(data)
                ScaffoldState.showSnackBar("Sign Up Successfully")
                restartApp()
            } catch (e: Exception) {
                ScaffoldState.showSnackBar(e.message ?: "Unknown Error")
            }
        }
    }

    fun authenticate(data: Intent, restartApp: () -> Unit) {
        launchCatching(ScaffoldState::showSnackBar) {
            try {
                googleAccountService.authenticate(data)
                ScaffoldState.showSnackBar("Sign In Successfully")
                restartApp()
            } catch (e: Exception) {
                ScaffoldState.showSnackBar(e.message ?: "Unknown Error")
            }
        }
    }
}