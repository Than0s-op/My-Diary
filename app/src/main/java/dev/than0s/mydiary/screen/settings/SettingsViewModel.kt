package dev.than0s.mydiary.screen.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.model.service.imple.GoogleAccountServiceImple
import dev.than0s.mydiary.screen.MyDiaryViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val accountService: GoogleAccountServiceImple
) : MyDiaryViewModel() {
    fun onDeleteAccountMyClick() {
        viewModelScope.launch {
            accountService.deleteAccount()
        }
    }

    fun onSignOutClick() {
        viewModelScope.launch {
            accountService.signOut()
        }
    }
}