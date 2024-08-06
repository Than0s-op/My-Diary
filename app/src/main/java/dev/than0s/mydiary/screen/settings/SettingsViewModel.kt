package dev.than0s.mydiary.screen.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.ScaffoldState
import dev.than0s.mydiary.model.service.imple.GoogleAccountServiceImple
import dev.than0s.mydiary.screen.MyDiaryViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val accountService: GoogleAccountServiceImple
) : MyDiaryViewModel() {
    fun isAnonymous(): Boolean {
        return accountService.isAnonymous
    }

    fun onDeleteAccountMyClick(restartApp: () -> Unit) {
        launchCatching(ScaffoldState::showSnackBar) {
            try {
                accountService.deleteAccount()
                restartApp()
            } catch (e: Exception) {
                ScaffoldState.showSnackBar(e.message ?: "Unknown Error")
            }
        }
    }

    fun onSignOutClick(restartApp: () -> Unit) {
        launchCatching(ScaffoldState::showSnackBar) {
            try {
                accountService.signOut()
                ScaffoldState.showSnackBar("Sign Out Successfully")
                restartApp()
            } catch (e: Exception) {
                ScaffoldState.showSnackBar(e.message ?: "Unknown Error")
            }
        }
    }
}