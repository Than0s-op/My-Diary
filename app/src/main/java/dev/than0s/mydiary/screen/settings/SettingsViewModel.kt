package dev.than0s.mydiary.screen.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.SPLASH_SCREEN
import dev.than0s.mydiary.model.service.imple.GoogleAccountServiceImple
import dev.than0s.mydiary.screen.MyDiaryViewModel
import dev.than0s.mydiary.ui.theme.AppState
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
        viewModelScope.launch {
            accountService.deleteAccount()
            restartApp()
        }
    }

    fun onSignOutClick(restartApp: () -> Unit) {
        viewModelScope.launch {
            accountService.signOut()
            AppState.snackbarHostState.showSnackbar("Sign Out Successfully")
            restartApp()
        }
    }
}