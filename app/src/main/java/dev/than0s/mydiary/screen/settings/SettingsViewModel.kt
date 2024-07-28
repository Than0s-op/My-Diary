package dev.than0s.mydiary.screen.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.model.service.imple.GoogleAccountServiceImple
import dev.than0s.mydiary.screen.MyDiaryViewModel
import dev.than0s.mydiary.AppState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val accountService: GoogleAccountServiceImple
) : MyDiaryViewModel() {
    fun isAnonymous(): Boolean {
        return accountService.isAnonymous
    }

    fun onDeleteAccountMyClick(restartApp: () -> Unit) {
        launchCatching(AppState.showSnackbar(viewModelScope)) {
            try {
                accountService.deleteAccount()
                restartApp()
            } catch (e: Exception) {
                AppState.showSnackbar(viewModelScope).invoke(e.message ?: "Unknown Error")
            }
        }
    }

    fun onSignOutClick(restartApp: () -> Unit) {
        launchCatching(AppState.showSnackbar(viewModelScope)) {
            try {
                accountService.signOut()
                AppState.snackbarHostState.showSnackbar("Sign Out Successfully")
                restartApp()
            } catch (e: Exception) {
                AppState.showSnackbar(viewModelScope).invoke(e.message ?: "Unknown Error")
            }
        }
    }
}